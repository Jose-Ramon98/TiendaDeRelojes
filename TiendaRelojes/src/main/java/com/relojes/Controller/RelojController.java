package com.relojes.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.relojes.Entity.Relojes;
import com.relojes.service.IRelojes;


@Controller
public class RelojController {

	@Autowired
	private IRelojes relojes;
	
	@GetMapping("")
	public String primera(Model model) {
		
		model.addAttribute("titulo","Bienvenido a la tienda de Relojes de referencia");
		
		return "home";
	}
	
	@GetMapping("/inicio")
	@PostMapping("/inicio")
	public String mostrarRelojes(Model model) {
		
		List<Relojes> lista = relojes.findAll();
		model.addAttribute("relojes", lista);
		return "inicio";
		
	}
	
	
	
	

}
