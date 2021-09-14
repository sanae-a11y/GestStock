package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Collecte;
@Repository
public interface CollecteRepository extends JpaRepository<Collecte, Long>{

}

