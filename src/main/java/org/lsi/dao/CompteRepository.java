package org.lsi.dao;

import java.util.List;

import org.lsi.entities.Compte;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompteRepository extends JpaRepository<Compte, String> {
	@Query("select c from Compte c where c.client.codeClient=:x")
	public List<Compte> getByClient(@Param("x") long code);
}
