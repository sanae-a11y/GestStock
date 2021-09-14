package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.projet.gestStock.model.Lcollecte;
@Repository
public interface LcollecteRepository extends JpaRepository<Lcollecte, Long>{

	
	Iterable<Lcollecte> findAllByNumero(int numero);

}
