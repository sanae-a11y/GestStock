package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Invent;
@Repository
public interface InventRepository extends JpaRepository<Invent, Long>{

}
