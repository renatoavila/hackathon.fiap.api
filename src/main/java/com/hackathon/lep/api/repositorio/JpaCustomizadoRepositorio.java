package com.hackathon.lep.api.repositorio;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.repository.NoRepositoryBean;

// -- https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-adding-custom-methods-into-all-repositories/
@NoRepositoryBean
public interface JpaCustomizadoRepositorio<T, ID> extends JpaRepository<T, ID> {

	Optional<T> getByChave(UUID chave); 

}
