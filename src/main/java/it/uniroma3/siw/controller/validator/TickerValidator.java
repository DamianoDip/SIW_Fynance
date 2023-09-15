package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Ticker;
import it.uniroma3.siw.repository.TickerRepository;

@Component
public class TickerValidator implements Validator {

	@Autowired TickerRepository tickerRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Ticker.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Ticker ticker  = (Ticker) target;
		
		if(ticker.getTickerName()!= null 
				
				        && tickerRepository.existsByTickerName(ticker.getTickerName())){
			
			errors.reject("ticker.duplicate");
				        	
				        }
			
		
	}

}
