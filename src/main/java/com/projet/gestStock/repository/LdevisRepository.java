package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Ldevis;

@Repository
public interface LdevisRepository extends JpaRepository<Ldevis, Long>{
	Iterable<Ldevis> findAllByNumero(int numero);
}
