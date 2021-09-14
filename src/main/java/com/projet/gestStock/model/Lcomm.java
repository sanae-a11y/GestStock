package com.projet.gestStock.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lcommande")
public class Lcomm {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  @Column(name="numero")
	  private int numero ;
	  private String code;
	  private String Libart;
	  private int pu;
	  private double qte;
	  private int tva;
	  private double totht;
	  private double tottva;
	  private double totttc;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Comm comm;

}
