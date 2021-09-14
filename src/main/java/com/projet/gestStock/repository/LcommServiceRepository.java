package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.gestStock.model.LcommService;

@Repository
public interface LcommServiceRepository  extends JpaRepository<LcommService, Long>{
	Iterable<LcommService> findAllByNumero(int numero);
}
