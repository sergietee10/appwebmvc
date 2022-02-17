package com.formacionspringboot.appwebmvc.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.formacionspringboot.appwebmvc.entity.Empleado;


public interface EmpleadoDao extends JpaRepository<Empleado, Long>{

	
	
}
