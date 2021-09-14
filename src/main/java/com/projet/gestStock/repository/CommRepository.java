package com.projet.gestStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Comm;
@Repository
public interface CommRepository extends JpaRepository<Comm, Long>{

}
