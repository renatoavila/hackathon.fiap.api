package com.hackathon.lep.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.lep.api.dto.SolicitacaoDto;
import com.hackathon.lep.api.modelo.Solicitacao;
import com.hackathon.lep.api.servico.SolicitacaoServico;

@RestController
public class SolicitacaoController {
 
	@Autowired
	private SolicitacaoServico solicitacaoServico;

	@RequestMapping(value = "api/criar", method = RequestMethod.POST)
	public ResponseEntity<?> criar(@RequestBody Solicitacao solicitacao) {
 
		try {

			Boolean retorno = solicitacaoServico.criar(solicitacao);

			if(retorno) {
				return ResponseEntity.noContent().build();
			}else {
				return ResponseEntity.badRequest().body("Falha ao criar solicitação");
			}			

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Falha ao criar solicitação.");
		}

	}
 
}
