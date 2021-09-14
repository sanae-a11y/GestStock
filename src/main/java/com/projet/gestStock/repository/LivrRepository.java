package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.projet.gestStock.model.Livr;
@Repository
public interface LivrRepository extends JpaRepository<Livr, Long>{
	
}
