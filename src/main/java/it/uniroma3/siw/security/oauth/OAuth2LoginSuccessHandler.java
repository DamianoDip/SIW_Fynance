package it.uniroma3.siw.security.oauth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.authentication.AuthenticationProvider;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
		
		String email = oAuth2User.getEmail();
		String name = oAuth2User.getName();
		
		User user  = this.userService.findUserByEmail(email);
		
		if ( user == null) { // non esiste un utente con questa email nel DB
			this.userService.saveUserAfterOauthLogin(name , email , AuthenticationProvider.GOOGLE);
		}
		else { // esiste un utente con quella mail , bisogna aggiornare 
			this.userService.updateUserAfterOauthLogin(user, AuthenticationProvider.GOOGLE);
		}
		
		
		
		
		
		
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	

}
