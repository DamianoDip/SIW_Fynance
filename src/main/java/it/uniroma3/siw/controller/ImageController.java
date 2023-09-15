package it.uniroma3.siw.controller;



import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.service.ImageService;

@Controller
public class ImageController {
    @Autowired
    ImageService imageService;

    
    
    @GetMapping("/display/image/{id}")
    public ResponseEntity<byte[]> displayItemImage(@PathVariable("id") Long id){
        
        Image img = this.imageService.findImageById(id);
        byte[] image = img.getBytes();
        HttpHeaders headers =  new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(image,headers,HttpStatus.OK);
    }
}