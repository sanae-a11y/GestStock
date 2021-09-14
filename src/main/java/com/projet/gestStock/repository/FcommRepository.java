package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Fcomm;
@Repository
public interface FcommRepository extends JpaRepository<Fcomm, Long>{

}
