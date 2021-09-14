package com.projet.gestStock.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mvtcheque")
public class Mvtcheque {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	 
	  private int annee;
	  private int numero;
	  private Date date_mvt;
	  private String num_compte;
	  private int numdeb;
	  private int nbre;

}
