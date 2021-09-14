package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.gestStock.model.RegFour;
@Repository
public interface RegFourRepository extends JpaRepository<RegFour, Long>{

}
