package com.projet.gestStock.model;

import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reglement")
public class Reglement {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int code;
	  private String lib_client;
	  @JsonFormat(pattern = "dd/MM/yyyy")
	  private LocalDate date_reg;
	  private String libelle;
	  private float total;
	  private String mode_reg;
	  private String nump;
	  private String banque;
	  private Date echeance;
	  @JsonManagedReference
	    @OneToMany(mappedBy = "reglement")
	    @Valid
	    private List<Lreglement> lreglements = new ArrayList<>();


}
