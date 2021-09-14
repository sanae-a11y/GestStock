package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.gestStock.model.Llivr;

@Repository
public interface LlivrRepository extends JpaRepository<Llivr, Long>{
	Iterable<Llivr> findAllByNumero(int numero);
}
