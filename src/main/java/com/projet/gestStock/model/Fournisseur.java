package com.projet.gestStock.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fournisseur")
public class Fournisseur {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String libelle;
	  private String responsable;
	  private String adresse;
	  private String ville;	  
	  private String tel;
	  private String email;
	  private String fax;
	  private String login;
	  private String pwd;
	  private String  matfisc;
	  private float   soldinit;
	  private float   soldef;


	
}