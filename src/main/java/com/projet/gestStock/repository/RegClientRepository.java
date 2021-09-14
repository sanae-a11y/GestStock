package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.gestStock.model.RegClient;
@Repository
public interface RegClientRepository extends JpaRepository<RegClient, Long>{

}
