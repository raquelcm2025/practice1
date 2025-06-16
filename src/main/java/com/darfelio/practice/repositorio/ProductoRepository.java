package com.darfelio.practice.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darfelio.practice.model.Productos;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer>{

}
