package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Lfact;

@Repository
public interface LfactRepository extends JpaRepository<Lfact, Long>{
	Iterable<Lfact> findAllByNumero(int numero);
}

