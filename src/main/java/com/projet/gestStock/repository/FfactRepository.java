package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Ffact;
@Repository
public interface FfactRepository extends JpaRepository<Ffact, Long>{

}
