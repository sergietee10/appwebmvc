package com.formacionspringboot.appwebmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formacionspringboot.appwebmvc.entity.Proyecto;


public interface ProyectoDao extends JpaRepository<Proyecto, Long>{

	
	
}