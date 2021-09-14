package com.projet.gestStock.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestStock.model.Article;
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

	Optional<Article> findByCode(String code);

}
