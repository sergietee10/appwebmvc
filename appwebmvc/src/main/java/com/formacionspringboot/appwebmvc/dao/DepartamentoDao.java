package com.formacionspringboot.appwebmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formacionspringboot.appwebmvc.entity.Departamento;


public interface DepartamentoDao extends JpaRepository<Departamento, Long>{

	
	
}