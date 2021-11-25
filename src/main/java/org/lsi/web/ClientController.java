package org.lsi.web;

import java.util.List;

import org.lsi.dao.ClientRepository;
import org.lsi.entities.Client;
import org.lsi.metier.ClientMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {
	@Autowired
	private ClientMetier cr;
	
	@GetMapping(path="/index")
	public String indexpage() {
		return "index";
	}

	
	@PostMapping("/client/saveClient")
	public String saveUser(Client c) {
		cr.saveClient(c);
		return "redirect:/client/listClient";
	}
	@GetMapping(path = "/client/listClient")
	private String client(Model model) {
		model.addAttribute("client", new Client());
		List<Client> listCls= cr.listClient();
		model.addAttribute("listClients", listCls);
		return "Client-ui";
	}
	
	
	

	
	
	
	

}
