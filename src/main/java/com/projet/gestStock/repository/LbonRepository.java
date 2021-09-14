package com.projet.gestStock.repository;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LbonRepository extends JpaRepository<Lbon, Long>{


	@Modifying
    @Transactional
    @Query("delete from Lbon e where numero = ?1")
    void deleteByNumero(int numero);
	Iterable<Lbon> findAllByNumero(int numero);
}
