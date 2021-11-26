package org.lsi.web;

import java.util.List;

import org.lsi.dao.ClientRepository;
import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.lsi.entities.CompteCourant;
import org.lsi.entities.CompteEpargne;
import org.lsi.entities.Employe;
import org.lsi.metier.ClientMetier;
import org.lsi.metier.CompteMetier;
import org.lsi.metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompteController {
	@Autowired
	private CompteMetier cptm;
	@Autowired
	private ClientRepository Clientm;
	@Autowired
	private ClientMetier cr ; 
	
	@Autowired
	private EmployeMetier emm;

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
		List<Employe> listEmp= emm.listEmployes();
		model.addAttribute("listEmployes", listEmp);
		List<Client> listCls= cr.listClient();
		model.addAttribute("listClients", listCls);
		return "Compte-ui";
	}
	
	@GetMapping("/comptesEpargne")
	public String ComptePageE(Model model) {
		model.addAttribute("compteE", new CompteEpargne());
		List<Employe> listEmp= emm.listEmployes();
		model.addAttribute("listEmployes", listEmp);
		List<Client> listCls= cr.listClient();
		model.addAttribute("listClients", listCls);
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
	  
	 @GetMapping("/client/compteClient/{codeClient}")
	 public String CompteClient(Model model ,@PathVariable(name = "codeClient") long CodeClient) {
		
		List<Compte> cclient = cptm.getComptClient(CodeClient);
		Client c = Clientm.getById(CodeClient);
			model.addAttribute("listCompetClient", cclient);
			model.addAttribute("nomClient",c.getNomClient());
		 return "compteClient";
	 }

}
