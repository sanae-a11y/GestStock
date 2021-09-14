package com.projet.gestStock.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devis")
public class Devis {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private Date date_mvt;
	  private int code;
	  private String libelle;
	  private String lib_client;
	  private float totht;
	  private float tottva;
	  private float totttc;
	  @JsonManagedReference
	    @OneToMany(mappedBy = "devis")
	    @Valid
	    private List<Ldevis> ldeviss = new ArrayList<>();

}
