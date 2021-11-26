package org.lsi.metier;


import java.util.Date;
import java.util.List;

import org.lsi.dao.CompteRepository;
import org.lsi.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CompteMetierImpl implements CompteMetier {
	
	
	@Autowired
	private CompteRepository compteRepository;

	@Override
	public Compte saveCompte(@RequestBody Compte cp) {
		// TODO Auto-generated method stub
		cp.setDateCreation(new Date());
		return compteRepository.save(cp);
	}

	@Override
	public Compte getCompte(String code) {
		// TODO Auto-generated method stub
		return compteRepository.findById(code).orElse(null);
	}
	public List<Compte> listCompte(){
		return compteRepository.findAll();
	}
	
	public List<Compte> getComptClient(long idClient) {
		
		return compteRepository.getByClient(idClient);
	}

	

}
