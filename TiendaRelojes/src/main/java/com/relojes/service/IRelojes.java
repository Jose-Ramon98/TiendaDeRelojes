package com.relojes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.relojes.Entity.Relojes;


public interface IRelojes {

	public List<Relojes>  findAll();
	
	public void save(Relojes relojes);
	
	
	public void delete (Integer id);
	
	
	public Relojes buscarPorId(Integer idReloj);
	
	
	public Relojes findOne(Integer id);
	
	
}
