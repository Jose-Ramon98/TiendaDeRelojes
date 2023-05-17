package com.relojes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.relojes.Dao.RelojesDao;
import com.relojes.Entity.Relojes;

@Service
public class RelojesImpl implements IRelojes{

	private List<Relojes> lista = null;
	
	
	
	@Autowired
	private RelojesDao relojesDao;

	
	
	@Override
	@Transactional(readOnly = true)
	public List<Relojes> findAll() {
		
		return (List<Relojes>) relojesDao.findAll(); 
	}

	@Override
	@Transactional
	public void save(Relojes relojes) {
		relojesDao.save(relojes);
		
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		relojesDao.deleteById(id);
		
	}

	@Override

	public Relojes buscarPorId(Integer idReloj) {
		
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Relojes findOne(Integer id) {
		
		return relojesDao.findById(id).orElse(null);//no es orElseThrow
	}

	@Override
	@Transactional(readOnly = true)
	public List<Relojes> findByMarca(String marca) {
	    return relojesDao.findByMarca(marca);
	}
	
}
	
	
	
	
	
	
	
	
	

