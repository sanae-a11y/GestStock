package com.projet.gestStock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "poids")
@Entity
public class Poids {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private double deb;
	  private double fin;
	  private double montant;

}
