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
@Table(name = "userRole")
public class UserRole {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private long iduser;
	  private long idrole;


}
