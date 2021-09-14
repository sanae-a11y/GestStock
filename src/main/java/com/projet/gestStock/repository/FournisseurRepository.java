package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Fournisseur;
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>{

}
