package com.formacionspringboot.appwebmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formacionspringboot.appwebmvc.entity.Proyecto;
import com.formacionspringboot.appwebmvc.service.ProyectoService;

@Controller
public class ProyectoController {

	@Autowired
	private ProyectoService servicio;

	@GetMapping("/proyectos")
	public String listarProyectos(Model model) {
		model.addAttribute("key", servicio.listarTodosLosProyectos());
		return "Proyecto/proyectos";
	}

	@GetMapping("proyectos/nuevo")
	public String formularioProyecto(Model modelo) {
		Proyecto newProyecto = new Proyecto();
		modelo.addAttribute("proyectoKey", newProyecto);
		return "Proyecto/nuevo_proyecto";
	}

	@PostMapping("/proyectos")
	public String guardarProyecto(@ModelAttribute("proyecto") Proyecto proyecto) {
		servicio.guardarProyecto(proyecto);
		return "redirect:/proyectos";
	}

	@GetMapping("/proyectos/editar/{id}")
	public String formularioEdicionProyecto(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("proyectoKey", servicio.obtenerProyectoPorId(id));
		return "Proyecto/editar_proyecto";
	}

	@PostMapping("/proyectos/editar/{id}")
	public String editarProyecto(@PathVariable Long id, @ModelAttribute("proyectoKey") Proyecto proyecto) {
		Proyecto proyectoEdit = servicio.obtenerProyectoPorId(id);
		proyectoEdit.setNombre(proyecto.getNombre());
		proyectoEdit.setFechaInicio(proyecto.getFechaInicio());
		proyectoEdit.setFechaFin(proyecto.getFechaFin());
		proyectoEdit.setActivo(proyecto.getActivo());
		servicio.guardarProyecto(proyectoEdit);
		return "redirect:/proyectos";
	}

	@GetMapping("/proyectos/borrar/{id}")
	public String formularioEliminarProyecto(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("proyectoKey", servicio.obtenerProyectoPorId(id));
		return "Proyecto/borrar_proyecto";
	}
	
	@PostMapping("/proyectos/borrar/{id}")
	public String eliminarProyecto(@PathVariable Long id) {
		servicio.eliminarProyecto(id);
		return "redirect:/proyectos";
	}
	
}