package com.darfelio.practice.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darfelio.practice.model.Categorias;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Integer>{

}
