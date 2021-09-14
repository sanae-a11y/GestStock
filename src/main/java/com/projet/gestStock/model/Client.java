package com.projet.gestStock.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {
	@Id
	  private long id;
	  private int code;
	  private String libelle;
	  private String adresse;
	  private String tel;
	  private String email;
	  private String fax;
	  private String login;
	  private String pwd;
	  private String smtva;
	  private String  matfisc;
	  private String  timbre;
	  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	  private Date cree;
	  private float   soldinit;
	  private float   soldef;


}