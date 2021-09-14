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
@Table(name = "societe")
public class Societe {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String libelle;
	  private String slibelle;
	  private String adresse;
	  private String tel1;
	  private String tel2;
	  private String fax;
	  private String matf;
	  private String rib;
	  private String banque;
	  private int numc;
	  private int numf;
	  private String registre;

}
