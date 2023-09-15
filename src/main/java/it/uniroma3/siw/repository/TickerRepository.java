package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import it.uniroma3.siw.model.Ticker;

public interface TickerRepository extends CrudRepository<Ticker, Long> {

	public List<Ticker> findByTickerName(String name);
	
	public List<Ticker> findAll();

	public boolean existsByTickerName(String tickerName);
	
}
