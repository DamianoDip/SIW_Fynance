package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Portafoglio;
import it.uniroma3.siw.model.Ticker;
import it.uniroma3.siw.repository.PortafoglioRepository;
import jakarta.transaction.Transactional;

@Service
public class PortafoglioService {


	@Autowired PortafoglioRepository portafoglioRepository;
	@Autowired TickerService tickerService;
	@Autowired CredentialsService credentialsService;

	public Portafoglio getPortafoglioById(Long id) {
		return this.portafoglioRepository.findById(id).get();
	}

	@Transactional
	public void savePortafoglio(Portafoglio portafoglio) {
		portafoglio.setOwner(this.credentialsService.getUserName());
		this.portafoglioRepository.save(portafoglio);

	}

	@Transactional 
	public void deletePortafoglio(Long id) {
		Portafoglio portafoglio = this.getPortafoglioById(id);
		this.portafoglioRepository.delete(portafoglio);
	}

	public List<Portafoglio> getPortafogliByOwner() {
		return this.portafoglioRepository.findAllByOwner(this.credentialsService.getUserName());
	}

	@Transactional
	public Portafoglio addTickerToPortafoglio(Long id_port, Long id_ticker) {
		Portafoglio portafoglio = this.getPortafoglioById(id_port);
		Ticker tickerDaInserire = this.tickerService.findTickerById(id_ticker);
		portafoglio.getTickers().add(tickerDaInserire);
		return this.portafoglioRepository.save(portafoglio);

	}

	@Transactional
	public Portafoglio removeTickerFromPortafoglio(Long id_port, Long id_ticker) {
		Portafoglio portafoglio = this.getPortafoglioById(id_port);
		Ticker tickerDaEliminare = this.tickerService.findTickerById(id_ticker);
		portafoglio.getTickers().remove(tickerDaEliminare);
		return this.portafoglioRepository.save(portafoglio);
	}


	public List<Ticker> getTickerAggiornati( Long id_portafoglio){
		Portafoglio portafoglio = this.getPortafoglioById(id_portafoglio);
		List<Ticker> tickerNotInPortafoglio = this.tickerService.findAllTickers();
		tickerNotInPortafoglio.removeAll(portafoglio.getTickers());
		return tickerNotInPortafoglio;
	}


}
