package org.lsi.web;

import java.util.List;

import org.lsi.entities.Compte;
import org.lsi.entities.Employe;
import org.lsi.metier.CompteMetier;
import org.lsi.metier.EmployeMetier;
import org.lsi.metier.OperationMetier;
import org.lsi.metier.PageOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OperationController {
	@Autowired
	private OperationMetier opm;
	
	
	@Autowired
	private CompteMetier cmpr;
	

	@Autowired
	private  EmployeMetier empm;
	
	@GetMapping("/Operations")
	public String operapage() {
		return "Operation-ui";
	}
	
	@GetMapping("consulterCompte")
	public String consulterCompe(Model model ,String codeCpt) {
		//get Compte By CodeCompte
		Compte c = cmpr.getCompte(codeCpt);
		model.addAttribute("codeCompte", codeCpt);
		model.addAttribute("compte", c);
		
		//list of Operations for CodeCompte = ?
		PageOperation pageOperations = opm.getOperation(codeCpt, 0, 3);
		model.addAttribute("ListOperations", pageOperations.getOperations());
		
		
		List<Employe> listEmp = empm.listEmployes();
		model.addAttribute("listEmployes", listEmp);
		return "Operation-ui";
	}
	
	@PostMapping("/saveOperation")
	public String saveOpera(Model model,String typeOperations, double montant,String codeCompte, String codeCompt2,Long idEmp){
		 if(typeOperations.equals("VR")) {
			 opm.verser(codeCompte, montant,idEmp);
		 }else if(typeOperations.equals("RE")) {
			 opm.retirer(codeCompte, montant, idEmp);
		 } else if(typeOperations.equals("VI")) {
			 opm.virement(codeCompte, codeCompt2, montant, idEmp);
		 }
		return "redirect:/consulterCompte?codeCpt="+codeCompte;
	}
}
