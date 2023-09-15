package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Azienda;
import it.uniroma3.siw.repository.AziendaRepository;

@Component
public class AziendaValidator implements Validator  {

	
	@Autowired 	AziendaRepository aziendaRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub

		return Azienda.class.equals(clazz);
			
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		Azienda azienda = (Azienda) target ;
		
		if ( azienda.getName()!= null  

				       && aziendaRepository.existsByName(azienda.getName())
				  
				) {
			errors.reject("azienda.duplicate");
		}
	}
}
