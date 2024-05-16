package com.ftninformatika.modul2.restoran.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ftninformatika.modul2.restoran.model.Porudzbina;
import com.ftninformatika.modul2.restoran.web.Dostava;

@Controller
@RequestMapping("/porudzbine")
public class PorudzbinaController {
  
  private Dostava dostava;
  
  @Autowired
  public PorudzbinaController(Dostava dostava) {
    this.dostava = dostava;
  }
  
  @GetMapping("")
	public String getAll(ModelMap request, @RequestParam(required = false, defaultValue = "") String param) {
		Collection<Porudzbina> rezultat = new ArrayList<>();
		
		for (Porudzbina itPorudzbina : dostava.getPorudzbine().values()) {
			if (param.equals("") || itPorudzbina.getKorisnik().getKorisnickoIme().equals(param) || String.valueOf(itPorudzbina.getRestoran().getId()).equals(param)) {
				rezultat.add(itPorudzbina);
			}
		}
		request.addAttribute("porudzbine", rezultat);
		return "porudzbine";
		
	}
	
	@GetMapping("/prikaz")
	public String get(ModelMap request, @RequestParam long id) {
		request.addAttribute("porudzbina", dostava.getPorudzbine().get(id));
		return "porudzbine-prikaz";
	}

}
