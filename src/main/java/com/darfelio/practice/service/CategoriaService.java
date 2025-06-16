package com.darfelio.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darfelio.practice.model.Categorias;
import com.darfelio.practice.repositorio.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public List<Categorias> listarTodos(){
		return repo.findAll();
	}
	
	public void registrar(Categorias bean) {
		repo.save(bean);
	}
	 public void actualizar(Categorias bean) {
		 repo.save(bean);
	 }
	 
	 public void eliminar(Integer cod) {
		 repo.deleteById(cod);
	 }
	 
	 public void buscar(Integer cod) {
		 repo.findById(cod).orElse(null);
	 }
}
