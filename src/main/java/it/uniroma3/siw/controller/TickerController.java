package it.uniroma3.siw.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.controller.validator.TickerValidator;
import it.uniroma3.siw.model.Ticker;
import it.uniroma3.siw.service.AziendaService;
import it.uniroma3.siw.service.TickerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@Controller
public class TickerController{

	@Autowired TickerService tickerService;
	@Autowired AziendaService aziendaService;
	@Autowired TickerValidator tickerValidator;

	@GetMapping("/indexTicker")
	public String getIndexTicker(){
		return "indexTicker.html";
	}

	@GetMapping("/admin/indexTickerAdmin")
	public String getIndexTickerAdmin(){
		return "admin/indexTickerAdmin.html";
	}


	@GetMapping( "/tickers")
	public String showTickers(Model model) {

		List<Ticker> tickers = this.tickerService.findAllTickers();
		model.addAttribute("tickers",tickers  ) ;
		return "tickers.html";
	}

	@GetMapping( "/admin/manageTickers")
	public String manageTickers(Model model) {
		List<Ticker> tickers = this.tickerService.findAllTickers();
		model.addAttribute("tickers",tickers ) ;
		return "admin/manageTickers.html";
	}

	@GetMapping("/ticker/{id}")
	public String showTicker( @PathVariable("id") Long id,Model model  ) {
		Ticker ticker = this.tickerService.findTickerById(id);
		if ( ticker!=null) {
			model.addAttribute("ticker",ticker);
            model.addAttribute("azienda", ticker.getAzienda());
		}
		else {
			return "error.html";
		}

		return "ticker.html";
	}


	@GetMapping("/admin/formUpdateTicker/{id}")
	public String formUpdateTicker( @PathVariable("id") Long id,Model model  ) {
		Ticker ticker = this.tickerService.findTickerById(id);

		if ( ticker!= null ) {
			model.addAttribute("ticker",ticker);
			model.addAttribute("azienda", ticker.getAzienda());

		}
		else {
			return "error.html";
		}
		return "admin/formUpdateTicker.html";
	}	

	@GetMapping("/admin/formNewTicker")
	public String formNewTicker (Model model) {
		model.addAttribute("ticker", new Ticker() );
		return "admin/formNewTicker.html";
	}


	@PostMapping("/admin/newTicker")
	public String newTicker( @Valid@ModelAttribute("ticker") Ticker ticker , BindingResult bindingResult , Model model) {
		tickerValidator.validate(ticker, bindingResult);
		if ( !bindingResult.hasErrors()) {
			model.addAttribute("ticker", this.tickerService.saveTicker(ticker));
			return "ticker.html";
		}
		else {
			return "admin/formNewTicker.html";
		}

	}

	@GetMapping("/admin/formUpdateTickerData/{old_ticker_id}")
	public String formUpdateTickerData (@PathVariable("old_ticker_id") Long id  ,Model model) {
		model.addAttribute("ticker", new Ticker() );
		model.addAttribute("oldTicker",this.tickerService.findTickerById(id) );
		return "admin/formUpdateTickerData.html";
	}


	@PostMapping("/admin/updateTickerData/{old_ticker_id}")
	public String UpdateTickerData(  @PathVariable("old_ticker_id") Long oldId  ,@Valid@ModelAttribute("ticker") Ticker ticker , BindingResult bindingResult , Model model) {
		tickerValidator.validate(ticker, bindingResult);
		if ( !bindingResult.hasErrors()) {

			model.addAttribute("ticker",this.tickerService.updateTickerData(ticker, oldId));
			return "ticker.html";
		}
		else {
			return "admin/formUpdateTickerData.html";

		}

	}

	@Transactional
	@GetMapping("/admin/addAziendaToTicker/{ticker_id}")
	public String  addAzienda(@PathVariable("ticker_id") Long id , Model model) {
	    model.addAttribute("aziendeSenzaTicker", this.tickerService.getAziendeSenzaTicker(this.aziendaService.findAllAziende()));
		model.addAttribute("ticker", this.tickerService.findTickerById(id));


		return "admin/aziendeToAdd.html";
	}


	@GetMapping("setAziendaToTicker/{id_Ticker}/{id_Azienda}")
	public String setAzienda(@PathVariable("id_Ticker") Long tickerId ,@PathVariable("id_Azienda") Long aziendaId , Model model) {
		this.tickerService.setAziendaToTicker(aziendaId , tickerId);
		model.addAttribute("azienda", this.aziendaService.findAziendaById(aziendaId));
		model.addAttribute("ticker", this.tickerService.findTickerById(tickerId));
		return "admin/formUpdateTicker.html";
	}

	
	
	@GetMapping("/admin/deleteTicker/{ticker_id}")
	public String deleteTicker ( @PathVariable("ticker_id") Long id , Model model ) {
		this.tickerService.deleteTicker(id);
		model.addAttribute("tickers" , this.tickerService.findAllTickers());
		return "tickers.html";
		
		
	}





}
