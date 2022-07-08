package com.hackathon.lep.api.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.lep.api.modelo.Solicitacao;
import com.hackathon.lep.api.repositorio.SolicitacaoRepositorios;

@Service
public class SolicitacaoServico {

	@Autowired
	SolicitacaoRepositorios solicitacaoRepositorio;

	public List<Solicitacao> obterTodos() {

		try {
			List<Solicitacao> solicitacao = (List<Solicitacao>) solicitacaoRepositorio.findAll();
			return solicitacao;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}

	}

	public Solicitacao obter(Integer id) {

		try {
			Optional<Solicitacao> solicitacao = solicitacaoRepositorio.findById(id);
			if (solicitacao.isPresent()) {
				return solicitacao.get();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);

		}
		return null;
	}

	public List<Solicitacao> obterPorEmail(String email) {

		try {
			List<Solicitacao> solicitacao = solicitacaoRepositorio.getByEmail(email);
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

	public Boolean atualizar(Solicitacao solicitacao) {

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
