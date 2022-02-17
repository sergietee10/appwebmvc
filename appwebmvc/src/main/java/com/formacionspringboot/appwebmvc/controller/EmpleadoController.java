package com.formacionspringboot.appwebmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formacionspringboot.appwebmvc.entity.Empleado;
import com.formacionspringboot.appwebmvc.service.EmpleadoService;

@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoService servicio;

	@GetMapping({ "/empleados", "/" })
	public String listarEmpleados(Model model) {
		model.addAttribute("key", servicio.listarTodosLosEmpleados());
		return "Empleado/empleados";
	}

	@GetMapping("empleados/nuevo")
	public String formularioEmpleado(Model modelo) {
		Empleado newEmpleado = new Empleado();
		modelo.addAttribute("empleadoKey", newEmpleado);
		return "Empleado/nuevo_empleado";
	}

	@PostMapping("/empleados")
	public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
		servicio.guardarEmpleado(empleado);
		return "redirect:empleados";
	}

	@GetMapping("/empleados/editar/{id}")
	public String formularioEdicionEmpleado(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("empleadoKey", servicio.obtenerEmpleadoPorId(id));
		return "Empleado/editar_empleado";
	}

	@PostMapping("/empleados/editar/{id}")
	public String editarEmpleado(@PathVariable Long id, @ModelAttribute("empleadoKey") Empleado empleado) {
		Empleado empleadoEdit = servicio.obtenerEmpleadoPorId(id);
		empleadoEdit.setNombre(empleado.getNombre());
		empleadoEdit.setApellido(empleado.getApellido());
		empleadoEdit.setEmail(empleado.getEmail());
		empleadoEdit.setTelefono(empleado.getTelefono());
		servicio.guardarEmpleado(empleadoEdit);
		return "redirect:/empleados";
	}

	@GetMapping("/empleados/borrar/{id}")
	public String formularioEliminarEmpleado(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("empleadoKey", servicio.obtenerEmpleadoPorId(id));
		return "Empleado/borrar_empleado";
	}
	
	@PostMapping("/empleados/borrar/{id}")
	public String eliminarEmpleado(@PathVariable Long id) {
		servicio.eliminarEmpleado(id);
		return "redirect:/empleados";
	}
	
}