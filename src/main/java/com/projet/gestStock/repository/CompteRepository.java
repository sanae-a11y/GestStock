package com.projet.gestStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Compte;
@Repository
public interface CompteRepository extends JpaRepository<Compte, Long>{

}