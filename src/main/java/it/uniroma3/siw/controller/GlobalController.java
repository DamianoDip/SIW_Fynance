package it.uniroma3.siw.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.siw.security.oauth.CustomOAuth2User;


@ControllerAdvice
public class GlobalController {
	
	@ModelAttribute("oauthName")
	public String getOauthName(){
		
		String name = null;
		
		try { name = this.getUserNameOauth(); }
		
		catch ( ClassCastException cce) {
			return null;
		}
		
		return name;
	}
	
	
    private String getUserNameOauth() throws ClassCastException{
    	CustomOAuth2User user = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
    		user =  (CustomOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		
    	}
    	
    	if (user!= null) {
		return user.getName();
    	}
    	return null;
	}


	@ModelAttribute("userDetails")
    public UserDetails getUser() {
        UserDetails user = null;
        


        try {
        	user = getUserDetails();
        }

        catch( ClassCastException cce) {
        	return null;
        }

  
        return user;
    }

    
    




	private UserDetails getUserDetails() throws ClassCastException{
		UserDetails user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            
        }
        
        return user;

	}
    
    
    
}