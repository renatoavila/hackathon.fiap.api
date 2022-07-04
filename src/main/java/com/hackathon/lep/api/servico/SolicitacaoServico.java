package com.hackathon.lep.api.servico;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.lep.api.modelo.Solicitacao;
import com.hackathon.lep.api.repositorio.SolicitacaoRepositorio;

@Service
public class SolicitacaoServico {

	@Autowired
	SolicitacaoRepositorio solicitacaoRepositorio;

	public Solicitacao obter(Integer id) {

		try {
			Solicitacao solicitacao = solicitacaoRepositorio.getById(id);
			return solicitacao;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}	
 
	}
	
	public Boolean criar(Solicitacao solicitacao) {

		try {
			 solicitacaoRepositorio.save(solicitacao);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}	
	}
	 

}
