package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Portafoglio;

public interface PortafoglioRepository  extends CrudRepository<Portafoglio, Long>{

	
	
	public List<Portafoglio> findAll();

	public boolean existsByName(String name);

	public List<Portafoglio> findAllByOwner(String owner);

}
