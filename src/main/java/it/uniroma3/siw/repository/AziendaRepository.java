package it.uniroma3.siw.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Azienda; 


public interface AziendaRepository extends CrudRepository<Azienda, Long> {

	
	public List<Azienda> findAll();

	public boolean existsByName(String name);
	
	

}
