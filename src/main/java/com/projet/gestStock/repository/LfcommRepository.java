package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Lfcomm;

@Repository
public interface LfcommRepository extends JpaRepository<Lfcomm, Long>{
	Iterable<Lfcomm> findAllByNumero(int numero);
}

