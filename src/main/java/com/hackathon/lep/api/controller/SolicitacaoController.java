package com.hackathon.lep.api.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder; 
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType; 
import org.springframework.web.bind.annotation.*; 
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays; 
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.hackathon.lep.api.dto.SolicitacaoDto;
import com.hackathon.lep.api.dto.ImagemDto;
import com.hackathon.lep.api.modelo.Solicitacao;
import com.hackathon.lep.api.servico.FileStorageServico;
import com.hackathon.lep.api.servico.SolicitacaoServico;

@RestController
public class SolicitacaoController {
	
	 
	private static final Logger logger = LoggerFactory.getLogger(SolicitacaoController.class);

	@Autowired
	private SolicitacaoServico solicitacaoServico;
	@Autowired
	private FileStorageServico fileStorageServico;
	
	@Value("${url}")
	private String url;
	

	@RequestMapping(value = "api/criar", method = RequestMethod.POST)
	public ResponseEntity<?> criar(@RequestBody SolicitacaoDto solicitacaoDto) {
 
		try {
			Solicitacao solicitacao = new Solicitacao();
			solicitacao.setTexto(solicitacaoDto.getTexto());
			solicitacao.setEmail(solicitacaoDto.getEmail());
			Boolean retorno = solicitacaoServico.criar(solicitacao);

			if(retorno) {
				return ResponseEntity.ok(solicitacao);
			}else {
				return ResponseEntity.badRequest().body("Falha ao criar solicitação");
			}			

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Falha ao criar solicitação.");
		}

	}
	
	@RequestMapping(value = "api/atualizar/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> atualizar(@PathVariable("id") Integer id, @RequestBody SolicitacaoDto solicitacaoDto) {
 
		try {
			Solicitacao solicitacao = solicitacaoServico.obter(id);
			solicitacao.setId(id);
			solicitacao.setTexto(solicitacaoDto.getTexto());
			solicitacao.setEmail(solicitacaoDto.getEmail());
			Boolean retorno = solicitacaoServico.atualizar(solicitacao);

			if(retorno) {
				return ResponseEntity.ok().build();
			}else {
				return ResponseEntity.badRequest().body("Falha ao atualizar solicitação");
			}			

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Falha ao atualizar solicitação.");
		}

	}
	
	@RequestMapping(value = "api/obterTodos", method = RequestMethod.GET)
	public ResponseEntity<?> obterTodos() {
 
		try {

			List<Solicitacao> retorno = solicitacaoServico.obterTodos();

			if(!retorno.isEmpty()) {
				return ResponseEntity.ok(retorno);
			}else {
				return ResponseEntity.notFound().build();
			}			

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Falha ao criar solicitação.");
		}

	}
	
	@RequestMapping(value = "api/obterEmail/{email}", method = RequestMethod.GET)
	public ResponseEntity<?> obterEmail(@PathVariable("email") String email) {
 
		try {

			List<Solicitacao> retorno = solicitacaoServico.obterPorEmail(email);

			if(!retorno.isEmpty()) {
				return ResponseEntity.ok(retorno);
			}else {
				return ResponseEntity.notFound().build();
			}			

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Falha ao criar solicitação.");
		}

	}
	
	@RequestMapping(value = "api/obter/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> obter(@PathVariable("id") Integer id) {
 
		try {

			Solicitacao retorno = solicitacaoServico.obter(id);

			if(retorno != null && retorno.getId() != null  && retorno.getId() > 0 ) {
				return ResponseEntity.ok(retorno);
			}else {
				return ResponseEntity.notFound().build();
			}			

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Falha ao buscar solicitação.");
		}

	}
	
	
	@RequestMapping(value = "api/salvarFoto/{id}", method = RequestMethod.POST)
	public ImagemDto salvarFoto(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) {
 
		 String fileName = fileStorageServico.storeFile(file);

	        String urlImagem = url
	                + "/getFoto/" 
	               + fileName;
	        
	        Solicitacao solicicao = solicitacaoServico.obter(id);
	        solicicao.setUrlImagem(urlImagem);
	        solicitacaoServico.atualizar(solicicao);
	        

	        return new ImagemDto(fileName, urlImagem,
	                file.getContentType(), file.getSize());

	}
	
    @GetMapping("/getFoto/{fileName:.+}")
    public ResponseEntity<Resource> getFoto(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageServico.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
 
}
