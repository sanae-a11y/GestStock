package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Lflivr;
@Repository
public interface LflivrRepository extends JpaRepository<Lflivr, Long>{

}