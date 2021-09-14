package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.gestStock.model.LRegFour;
@Repository
public interface LRegFourRepository extends JpaRepository<LRegFour, Long>{
	Iterable<LRegFour> findAllByNumero(int numero);
}
