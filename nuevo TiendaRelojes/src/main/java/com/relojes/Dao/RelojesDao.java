package com.relojes.Dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.relojes.Entity.Relojes;

public interface RelojesDao extends PagingAndSortingRepository<Relojes, Integer>{

	List<Relojes> findByMarca(String marca);


	

}
