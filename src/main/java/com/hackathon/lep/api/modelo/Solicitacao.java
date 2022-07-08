package com.hackathon.lep.api.modelo;

 
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;
 
 
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
  

@Getter
@Setter
@ToString(callSuper=true, includeFieldNames=true)
@Table(name = "solicitacao"  )
@Entity 
public class Solicitacao extends Entidade<Integer> {
	 
	private String email;	

	private String texto;

	private String atendimento;
	  
	private String urlImagem;
 
}
