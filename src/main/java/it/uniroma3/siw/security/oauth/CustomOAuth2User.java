package it.uniroma3.siw.security.oauth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User{

	private OAuth2User oauth2user;
	
	public CustomOAuth2User ( OAuth2User oauth2user) {
		
		 this.oauth2user = oauth2user;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return this.oauth2user.getAttributes();	
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.oauth2user.getAuthorities();
	}

	@Override
	public String getName() {
		return oauth2user.getAttribute("name");
	}
	

	
	
	public String getEmail() {
		return oauth2user.getAttribute("email");		
	}

}
