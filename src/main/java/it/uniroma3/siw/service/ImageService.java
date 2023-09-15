package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.ImageRepository;
import jakarta.transaction.Transactional;

@Service
public class ImageService {

	
	@Autowired ImageRepository imageRepository;

	public Image findImageById(Long id) {
		return this.imageRepository.findById(id).get();
	}

	
	@Transactional
	public void saveImage(Image movieimg) {
		this.saveImage(movieimg);
		
	}
	
}
