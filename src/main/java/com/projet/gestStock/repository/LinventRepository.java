package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Linvent;

@Repository
public interface LinventRepository extends JpaRepository<Linvent, Long>{
	Iterable<Linvent> findAllByNumero(int numero);
}
