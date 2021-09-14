package com.projet.gestStock.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Depot;
@Repository
public interface DepotRepository extends JpaRepository<Depot, Long>{

	List<Depot> findByCode(int code);

}
