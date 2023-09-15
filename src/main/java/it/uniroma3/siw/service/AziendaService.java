package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Azienda;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.AziendaRepository;
import it.uniroma3.siw.repository.ImageRepository;
import jakarta.transaction.Transactional;

@Service
public class AziendaService {

	@Autowired AziendaRepository aziendaRepository;
	@Autowired ImageRepository imageRepository;

	public List<Azienda> findAllAziende() {
		return this.aziendaRepository.findAll();
	}

	public Azienda findAziendaById(Long aziendaId) {
		return this.aziendaRepository.findById(aziendaId).get();
		
	}

	
	@Transactional
	public void saveAzienda(Azienda azienda, MultipartFile image) throws IOException {
		Image movieimg = new Image(image.getBytes());
		this.imageRepository.save(movieimg);
		azienda.setImage(movieimg);
		this.aziendaRepository.save(azienda);
		
	}

	@Transactional
	public void deleteAzienda(Long id) {
		this.aziendaRepository.delete(this.findAziendaById(id));
	}
	
}
