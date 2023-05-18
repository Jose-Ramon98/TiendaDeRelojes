package com.relojes.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.relojes.Entity.Relojes;


public interface IRelojes {

	public List<Relojes>  findAll();
	
	public Page<Relojes>  findAll(Pageable pageable);
	
	public void save(Relojes relojes);
	
	
	public void delete (Integer id);
	
	
	public Relojes buscarPorId(Integer idReloj);
	
	
	public Relojes findOne(Integer id);

	public List<Relojes> findByMarca(String marca);


	
}
