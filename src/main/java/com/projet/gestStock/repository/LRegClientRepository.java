package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.gestStock.model.LRegClient;
@Repository
public interface LRegClientRepository extends JpaRepository<LRegClient, Long>{
	Iterable<LRegClient> findAllByNumero(int numero);
}
