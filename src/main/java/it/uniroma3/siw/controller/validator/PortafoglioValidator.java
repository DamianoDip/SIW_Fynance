package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Portafoglio;
import it.uniroma3.siw.repository.PortafoglioRepository;

@Component
public class PortafoglioValidator implements Validator {

	@Autowired PortafoglioRepository portafoglioRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Portafoglio.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Portafoglio portafoglio = (Portafoglio) target;
		
		if ( portafoglio.getName()!= null 
			   && portafoglioRepository.existsByName(portafoglio.getName())) {
			
			errors.reject("portafoglio.duplicate");
			
		}
		
	}

}
