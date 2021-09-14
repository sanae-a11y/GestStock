package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Fact;
@Repository
public interface FactRepository extends JpaRepository<Fact, Long>{

}
