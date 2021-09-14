package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.gestStock.model.Tva;
@Repository
public interface TvaRepository extends JpaRepository<Tva, Long>{

}
