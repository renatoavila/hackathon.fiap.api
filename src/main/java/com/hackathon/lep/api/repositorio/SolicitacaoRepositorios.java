package com.hackathon.lep.api.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hackathon.lep.api.modelo.Solicitacao;

public interface SolicitacaoRepositorios extends CrudRepository<Solicitacao, Integer> {
	
	List<Solicitacao> getByEmail(String email);
	 
 
}