package it.uniroma3.siw.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Azienda;
import it.uniroma3.siw.model.Ticker;
import it.uniroma3.siw.repository.TickerRepository;
import jakarta.transaction.Transactional;

@Service
public class TickerService {

	@Autowired TickerRepository tickerRepository;
	@Autowired AziendaService aziendaService;

	public List<Ticker> findAllTickers() {
		return this.tickerRepository.findAll();
	}

	public Ticker findTickerById(Long id) {
		return this.tickerRepository.findById(id).get();
	}


	@Transactional
	public Ticker saveTicker(Ticker ticker) {

		return this.tickerRepository.save(ticker);
	}

	@Transactional
	public Ticker updateTickerData(Ticker tickerWithNewData, Long oldId) {
		// TODO Auto-generated method stub
		Ticker tickerWithOldData = this.findTickerById(oldId);

		tickerWithOldData.setAttuale(tickerWithNewData.getAttuale());
		tickerWithOldData.setMax(tickerWithNewData.getMax());
		tickerWithOldData.setMin(tickerWithNewData.getMin());

		return this.saveTicker(tickerWithOldData);

	}

	public List<Azienda> getAziendeSenzaTicker(List<Azienda> aziendeSenzaTicker ) {
		List<Azienda> aziendeConTicker = new LinkedList<>();
		List<Ticker> tickers = new LinkedList<>(this.tickerRepository.findAll());
		for ( int i = 0 ; i< tickers.size() ; i++ ) {
			Azienda azienda = tickers.get(i).getAzienda();
			if( azienda!= null) {
				aziendeConTicker.add(azienda);
			}
		}
		aziendeSenzaTicker.removeAll(aziendeConTicker);
		return aziendeSenzaTicker;

	}

	@Transactional
	public void setAziendaToTicker(Long aziendaId , Long tickerId) {
		Azienda azienda = this.aziendaService.findAziendaById(aziendaId);
		Ticker ticker = this.findTickerById(tickerId);
		ticker.setAzienda(azienda);		
		this.saveTicker(ticker);
	}


	@Transactional
	public void deleteTicker(Long id) {	
		Ticker ticker = this.findTickerById(id);
		this.tickerRepository.delete(ticker);
	}



}
