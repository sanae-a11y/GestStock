package com.projet.gestStock.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facture")
public class FactFour {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	  private Date date_mvt;
	  private int mat;
	  private int code;
	  private String libelle;
	  private String libfour;
	  private int numdep;
	  private int nbre;
	  private double totht;
	  private double tottva;
	  private double timbre;
	  private double totttc;
	  @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "factFour", fetch=FetchType.EAGER)
    @Valid
	  private List<Lfacture> lfactures = new ArrayList<>();

}
