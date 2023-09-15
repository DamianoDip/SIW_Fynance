package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.controller.validator.PortafoglioValidator;
import it.uniroma3.siw.model.Portafoglio;
import it.uniroma3.siw.service.PortafoglioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller 
public class PortafoglioController {

	@Autowired PortafoglioService portafoglioService;
	@Autowired PortafoglioValidator portafoglioValidator;


	@GetMapping("/indexPortafoglio")
	public String getIndexPortafoglio(){
		return "default/indexPortafoglio.html";
	}

	@GetMapping("/default/portafogli")
	public String showPortafogli (  Model model  ) {
		model.addAttribute("portafogli", this.portafoglioService.getPortafogliByOwner());
		return "default/portafogli.html"; 
	}


	@GetMapping("/default/portafoglio/{portafoglio_id}")
	public String showPortafoglio ( @PathVariable("portafoglio_id") Long id , Model model) {
		Portafoglio portafoglio = this.portafoglioService.getPortafoglioById(id);
		if ( portafoglio!= null) {
			model.addAttribute("portafoglio", portafoglio);
			model.addAttribute("listaTicker", portafoglio.getTickers());
		}
		else {
			return "error.html";
		}
		return "default/portafoglio.html";
	}

	@GetMapping("/default/formNewPortafoglio")
	public String formNewPortafoglio (Model model) {
		model.addAttribute("portafoglio", new Portafoglio() );
		return "default/formNewPortafoglio.html";
	}

	

	@Transactional
	@PostMapping("/default/newPortafoglio")
	public String newPortafoglio( @Valid@ModelAttribute("portafoglio") Portafoglio portafoglio , BindingResult bindingResult , Model model) {
		portafoglioValidator.validate(portafoglio, bindingResult);
		if ( !bindingResult.hasErrors()) {
			this.portafoglioService.savePortafoglio(portafoglio);
			model.addAttribute("portafoglio", portafoglio);
			return "default/portafoglio.html";
		}
		else {
			return "default/formNewPortafoglio.html";
		}
	}



	@GetMapping("/default/formUpdatePortafoglio/{id_portafoglio}")
	public String addTickersToPortafogli( @PathVariable("id_portafoglio") Long id_port    , Model model) {
		Portafoglio portafoglio = this.portafoglioService.getPortafoglioById(id_port);
		model.addAttribute("portafoglio", portafoglio);
		model.addAttribute("tickerInPortafoglio", portafoglio.getTickers());
		model.addAttribute("tickerNotInPortafoglio",this.portafoglioService.getTickerAggiornati(id_port));
		return "default/formUpdatePortafoglio.html";
	}

	@Transactional
	@GetMapping("/default/addTicker/{id_portafogli}/{id_ticker}")
	public String  addTicker ( @PathVariable("id_portafogli") Long id_port , @PathVariable("id_ticker") Long id_ticker , Model model){
		
		Portafoglio portafoglio = this.portafoglioService.addTickerToPortafoglio(id_port , id_ticker);
		model.addAttribute("portafoglio", portafoglio);
		model.addAttribute("tickerInPortafoglio", portafoglio.getTickers());
		model.addAttribute("tickerNotInPortafoglio",this.portafoglioService.getTickerAggiornati(id_port));
		return "default/formUpdatePortafoglio.html";
	}


	@Transactional
	@GetMapping("/default/removeTicker/{id_portafogli}/{id_ticker}")
	public String  removeTicker ( @PathVariable("id_portafogli") Long id_port , @PathVariable("id_ticker") Long id_ticker , Model model){
		Portafoglio portafoglio = this.portafoglioService.removeTickerFromPortafoglio(id_port, id_ticker);
		model.addAttribute("portafoglio", portafoglio);
		model.addAttribute("tickerInPortafoglio", portafoglio.getTickers());
		model.addAttribute("tickerNotInPortafoglio", this.portafoglioService.getTickerAggiornati(id_port));
		return "default/formUpdatePortafoglio.html";
	}
	
	
	
	@GetMapping("/default/deletePortafoglio/{port_id}")
	public String deletePortafoglio ( @PathVariable("port_id") Long id , Model model ) {
		this.portafoglioService.deletePortafoglio(id);
		return "default/indexPortafoglio.html";
	}


	

}
