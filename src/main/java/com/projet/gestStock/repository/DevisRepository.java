package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Devis;
@Repository
public interface DevisRepository extends JpaRepository<Devis, Long>{

}

