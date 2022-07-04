package com.hackathon.lep.api.modelo;

 
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

	private String reclamacao;
	
	private String urlImagem;	 	  
   
}
