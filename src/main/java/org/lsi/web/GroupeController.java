package org.lsi.web;

import java.util.List;

import org.lsi.dao.EmployeRepository;
import org.lsi.dao.GroupeRepository;
import org.lsi.entities.Client;
import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.lsi.metier.ClientMetier;
import org.lsi.metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class GroupeController {
	@Autowired
private GroupeRepository grprepo;
	@Autowired
	private ClientMetier cr;
	@Autowired
	private EmployeMetier emm;
	@Autowired
	private EmployeRepository empr;

	@GetMapping("/groupe")
	public String PageGroup(Model model) {
		List<Client> listCls= cr.listClient();
		model.addAttribute("listClients", listCls);
		model.addAttribute("groupe", new Groupe());
		List<Employe> listEmp= emm.listEmployes();
		model.addAttribute("listEmployes", listEmp);
		List<Groupe> listg = grprepo.findAll();
		model.addAttribute("Listgroup", listg);
		return "groupe";
	}

		@PostMapping("/saveGroupe")
		public String saveGroup(Groupe g) {
			grprepo.save(g);
			return "redirect:/groupe";
		}
		
		@PostMapping(value="/affecterGroupeEmp")
		public String affecterGroupe(Long codeEmploye, Long codeGroupe) {
			Groupe g=grprepo.findById(codeGroupe).get();
			Employe e=empr.findById(codeEmploye).get();
			 g.getEmploye().add(e);    //ajouter au tableau
			e.getGroupes().add(g);

			grprepo.save(g);    //ajouter dans la base de donnes 
			empr.save(e);
			
			return "redirect:/groupe";   //refresh la page
		}
		
		
		
		


}
