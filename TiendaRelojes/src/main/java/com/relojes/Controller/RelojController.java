package com.relojes.Controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/reloj/nuevo")

	public String crear (Model model) {
		model.addAttribute("watch", new Relojes());
		return "crear";
	
	}
	@PostMapping("/reloj/nuevo")
	public String crearLibro(@ModelAttribute Relojes reloj, Model model) {
		relojes.save(reloj);
		return "redirect:/inicio";
		
	}
	
	@PostMapping("/reloj/{id}/eliminar")
	public String borrar (@PathVariable(value="id")Integer id, Model model) {
		
		relojes.delete(id);
		
		return "redirect:/inicio";
		
	}
	@GetMapping("/reloj/{id}/editar")
	public String editar(@PathVariable(value = "id") Integer id,Model model) {
	Relojes reloj = relojes.findOne(id);
	   	
	//La palabra reloj es muy importante por esto -<form method="post" th:action="@{/reloj/{id}/editar(id=${reloj.id})}" th:object="${reloj}">
    model.addAttribute("reloj", reloj);
    return "editar-reloj";
	
	}
	
	
	@PostMapping("/reloj/{id}/editar")
	public String guardarReloj(@PathVariable("id") Integer id, @ModelAttribute("reloj") Relojes reloj, Model model) {
	    Relojes relojActualizado = relojes.findOne(id);
	    if (relojActualizado == null) {
	        model.addAttribute("error", "El ID del libro no existe en la BBDD!");
	        return "editar-reloj";
	    }
	    relojActualizado.setMarca(reloj.getMarca());
	    relojActualizado.setModelo(reloj.getModelo());
	    relojActualizado.setPrecio(reloj.getPrecio());
	    relojes.save(relojActualizado);
	    return "redirect:/inicio";
	}

	@GetMapping("/reloj/ordenar")
	public String ordenarRelojes(@RequestParam("orden") String orden, Model model) {
	    List<Relojes> lista = relojes.findAll();
	    if (orden.equals("descendente")) {
	        lista.sort(Comparator.comparing(Relojes::getPrecio).reversed());
	    } else {
	        lista.sort(Comparator.comparing(Relojes::getPrecio));
	    }
	    model.addAttribute("relojes", lista);
	    return "inicio";
	}
	
	@GetMapping("/reloj/filtrar")
	public String filtrarRelojesPorMarca(@RequestParam("marca") String marca, Model model) {
	    List<Relojes> lista = relojes.findByMarca(marca);
	    model.addAttribute("relojes", lista);
	    return "inicio";
	}
	
}
	
	
	










