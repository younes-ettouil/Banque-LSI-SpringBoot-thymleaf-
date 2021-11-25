package org.lsi.web;

import java.util.List;

import org.lsi.dao.OperationRepository;
import org.lsi.entities.Compte;
import org.lsi.entities.CompteCourant;
import org.lsi.entities.CompteEpargne;
import org.lsi.entities.Employe;
import org.lsi.entities.Operation;
import org.lsi.metier.ClientMetier;
import org.lsi.metier.CompteMetier;
import org.lsi.metier.EmployeMetier;
import org.lsi.metier.OperationMetier;
import org.lsi.metier.PageOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompteController {
	@Autowired
	private CompteMetier cptm;

	@GetMapping("/succes/{codeCpt}")
	public String pageSucces(Model model,@PathVariable(name = "codeCpt") String codeCpt) {
		
			//get Compte By CodeCompte
			Compte c = cptm.getCompte(codeCpt);
			model.addAttribute("compte", c);
			
		return "compteCree";
	}
	
	@GetMapping("/comptesCourant")
	public String ComptePage(Model model) {
		model.addAttribute("compteC", new CompteCourant());
		return "Compte-ui";
	}
	
	@GetMapping("/comptesEpargne")
	public String ComptePageE(Model model) {
		model.addAttribute("compteE", new CompteEpargne());
		return "Compte-ui";
	}
	
	  @PostMapping("/saveCompteCourant") 
	  public String saveComptC(CompteCourant c) {
		  cptm.saveCompte(c); 
	return "redirect:/succes/"+c.getCodeCompte();
	}
	  @PostMapping("/saveCompteEpargne") 
	  public String saveComptE(CompteEpargne c) {
		  cptm.saveCompte(c); 
	return  "redirect:/succes/"+c.getCodeCompte();
	}
	  

}
