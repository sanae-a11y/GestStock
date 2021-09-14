package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Flivr;
@Repository
public interface FlivrRepository extends JpaRepository<Flivr, Long>{

}