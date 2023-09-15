package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.authentication.AuthenticationProvider;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The UserService handles logic for Users.
 */
@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;
    
    @Autowired
	protected CredentialsRepository credentialsRepository;

    /**
     * This method retrieves a User from the DB based on its ID.
     * @param id the id of the User to retrieve from the DB
     * @return the retrieved User, or null if no User with the passed ID could be found in the DB
     */
    @Transactional
    public User getUser(Long id) {
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a User in the DB.
     * @param user the User to save into the DB
     * @return the saved User
     * @throws DataIntegrityViolationException if a User with the same username
     *                              as the passed User already exists in the DB
     */
    @Transactional
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = this.userRepository.findAll();
        for(User user : iterable)
            result.add(user);
        return result;
    }

	public User findUserByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	
	@Transactional
	public void saveUserAfterOauthLogin(String name, String email, AuthenticationProvider provider) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setSurname(name);
		user.setAuthProvider(provider);
		Credentials credentials = new Credentials();
		credentials.setRole(Credentials.DEFAULT_ROLE);
		credentials.setUsername(name);
		this.saveUser(user);
		credentials.setUser(user);
		this.credentialsRepository.save(credentials);
		
		
	}

	public void updateUserAfterOauthLogin(User user, AuthenticationProvider google) {
		user.setAuthProvider(google);
		this.saveUser(user);
		
	}
}
