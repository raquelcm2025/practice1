package com.darfelio.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.darfelio.practice.model.Productos;
import com.darfelio.practice.repositorio.ProductoRepository;
import com.darfelio.practice.service.CategoriaService;
import com.darfelio.practice.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductosController {
	
	private final ProductoRepository productoRepository;
	 @Autowired
	 private ProductoService prodServicio;
	 
	 @Autowired
	 private CategoriaService cateServicio;
	 
	 ProductosController(ProductoRepository productoRepository){
		 this.productoRepository = productoRepository;
	 }
	 
	 //ruta o endpoint que permita comunicarse con el cliente
	 @RequestMapping("/lista")
	 public String listar(Model model) {
		 List<Productos> datos= prodServicio.listarTodos();
		//enviar el arreglo datos dentro de un atributo
		 model.addAttribute("lista", datos);
		 model.addAttribute("categoria",cateServicio.listarTodos());
		 model.addAttribute("producto", new Productos());
		 return  "producto";
	 }
	 
	 @GetMapping("/register")
	 public String mostrarForm(Model model) {
		 model.addAttribute("producto", new Productos());
		 model.addAttribute("categorias", cateServicio.listarTodos());
		 return "registro";
	 }
	 
	 @RequestMapping("/guardar")
	 public String guardar(@ModelAttribute("producto") Productos bean,
			 RedirectAttributes redirect) {
		 try {
			 if(bean.getCodigo()==null){
				 prodServicio.registrar(bean);
				 redirect.addFlashAttribute("MENSAJE", "Producto Registrado");
			 }
			 else {
				 prodServicio.actualizar(bean);
				 redirect.addFlashAttribute("MENSAJE", "Producto Actualizado");
			 }
		 } catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:/productos/lista";
	 }
	 
	 @GetMapping("/editar/{codigo}")
	 public String editarProducto(@PathVariable("codigo") Integer cod, Model model) {
	     Productos producto = prodServicio.buscarPorID(cod); // o getById(id)
	     model.addAttribute("producto", producto);
	     model.addAttribute("categorias", cateServicio.listarTodos());
	     return "registro"; // nombre exacto del HTML
	 }
	 
	 @GetMapping("/eliminar")
	 @ResponseBody
	 public String eliminar(@RequestParam("codigo") Integer codigo) {
		 prodServicio.eliminar(codigo);
		 return "ok";
	 }

	 
	 @GetMapping("/buscar")
	 @ResponseBody //mostrar JSON de Medicamento
	 public Productos buscar(@RequestParam("codigo") Integer cod) {
		 return prodServicio.buscarPorID(cod);
	 }
}
