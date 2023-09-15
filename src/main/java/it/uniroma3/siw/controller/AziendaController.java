package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.siw.controller.validator.AziendaValidator;
import it.uniroma3.siw.model.Azienda;
import it.uniroma3.siw.service.AziendaService;
import jakarta.validation.Valid;

@Controller
public class AziendaController {


	@Autowired AziendaService aziendaService;
	@Autowired AziendaValidator aziendaValidator;

	@GetMapping("/indexAzienda")
	public String getIndexAzienda(){
		return "indexAzienda.html";
	}

	@GetMapping("/admin/indexAziendaAdmin")
	public String getIndexAziendaAdmin(){
		return "admin/indexAziendaAdmin.html";
	}

	@GetMapping( "/aziende")
	public String showAziende(Model model) {
		model.addAttribute("aziende", this.aziendaService.findAllAziende() ) ;
		return "aziende.html";
	}
	
	@GetMapping( "/admin/aziendeAdmin")
	public String showTickers(Model model) {
		model.addAttribute("aziende", this.aziendaService.findAllAziende() ) ;
		return "admin/aziendeAdmin.html";
	}

	@GetMapping("/azienda/{id}")
	public String showTicker( @PathVariable("id") Long id,Model model  ) {
		Azienda azienda = this.aziendaService.findAziendaById(id);
		model.addAttribute("azienda",azienda);
		return "azienda.html";
	}


	@GetMapping("/admin/formNewAzienda")
	public String formNewAzienda (Model model) {
		model.addAttribute("azienda", new Azienda() );
		return "admin/formNewAzienda.html";
	}


	@PostMapping("/admin/newAzienda")
	public String newAzienda( @RequestParam("file") MultipartFile image ,@Valid @ModelAttribute("azienda") Azienda azienda , BindingResult bindingResult , Model model) throws IOException{
		this.aziendaValidator.validate(azienda,bindingResult);
		if ( !bindingResult.hasErrors()) {
			this.aziendaService.saveAzienda(azienda , image);
//			
//			Image movieimg = new Image(image.getBytes());
//			 this.imageRepository.save(movieimg);
//			
//			azienda.setImage(movieimg);
//
//			this.aziendaService.saveAzienda(azienda);
			model.addAttribute("azienda", azienda);
			return "azienda.html";
		}
		else {
			return  "admin/formNewAzienda.html";
		}

	}
	
	@GetMapping("/admin/deleteAzienda/{azienda_id}")
	public String deleteAzienda ( @PathVariable("azienda_id") Long id , Model model ) {
		this.aziendaService.deleteAzienda(id);
		model.addAttribute("aziende", this.aziendaService.findAllAziende());
		return "aziende.html";
		
		
	}

}
