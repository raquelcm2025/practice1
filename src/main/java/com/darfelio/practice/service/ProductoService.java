package com.darfelio.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darfelio.practice.model.Productos;
import com.darfelio.practice.repositorio.ProductoRepository;

@Service
public class ProductoService {
	//Inyeccion de depencias
	@Autowired
	private ProductoRepository repo;
	
	//metodos para listar
	public List<Productos> listarTodos(){
		return repo.findAll();
	}
	
	public void registrar(Productos bean) {
		repo.save(bean);
	}
	
	public void actualizar(Productos bean) {
		repo.save(bean);
	}
	
	public void eliminar(Integer cod) {
		repo.deleteById(cod);
	}
	
	public Productos buscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
}
