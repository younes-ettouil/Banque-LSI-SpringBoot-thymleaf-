package org.lsi.web;

import java.util.List;

import org.lsi.entities.Client;
import org.lsi.entities.Employe;
import org.lsi.metier.ClientMetier;
import org.lsi.metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeController {
	@Autowired
	private EmployeMetier empm;
	
	@PostMapping("/employe/saveEmp")
	public String saveUser(Employe e) {
		empm.saveEmploye(e);
		return "redirect:/employe";
	}
	@GetMapping(path = "/employe")
	private String client(Model model) {
		model.addAttribute("employe", new Employe());
		List<Employe> listEmp= empm.listEmployes();
//		System.out.println(listEmp.get(0).getEmployeSup());
		model.addAttribute("listEmployes", listEmp);
		return "Employe-ui";
	}
}
