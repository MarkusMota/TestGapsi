package com.gapsi.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class WelcomeController {


	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String listar(Model model) {
		
		
		model.addAttribute("candidato", "Benvenido Candidato 01");
		model.addAttribute("version", "versión 0.0.1");
		return "index";
	}

	
}