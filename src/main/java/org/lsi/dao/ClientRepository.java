package org.lsi.dao;


import org.lsi.entities.Client;
import org.lsi.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query("select o from Operation o where o.compte.codeCompte=:x")
	public Page<Operation> getOperations(@Param("x") String code, Pageable pageable);
}
