package pw.io.booker.service;

import javax.security.sasl.AuthenticationException;
import javax.transaction.Transactional;

import pw.io.booker.model.Authentication;
import pw.io.booker.model.Customer;
import pw.io.booker.model.User;
import pw.io.booker.repo.AuthenticationRepository;
import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.util.TokenCreator;

public class AuthenticationService {

	private AuthenticationRepository authenticationRepository;
	private CustomerRepository customerRepository;
	private TokenCreator tokenCreator;
	
	public AuthenticationService(AuthenticationRepository authenticationRepository,
			CustomerRepository customerRepository, TokenCreator tokenCreator) {
		super();
		this.authenticationRepository = authenticationRepository;
		this.customerRepository = customerRepository;
		this.tokenCreator = tokenCreator;
	}
	
	@Transactional
	public Authentication getAuthentication(String token) {
		return authenticationRepository.findByToken(token);
	}
	
	@Transactional
	public Authentication loginUser(User user) {
		Customer customer = customerRepository.findByUsername(user.getUsername());
		if(customer != null && customer.getPassword().equals(user.getPassword())) {
			Authentication authentication = new Authentication();
			authentication.setCustomer(customer);
			authentication.setToken(tokenCreator.encode(customer));
			deletePreviousLogin(customer);
			authenticationRepository.save(authentication);
			return authentication;
		}
		return null;
	}
	
	@Transactional
	public void logoutUser(String token) {
		Authentication authentication = authenticationRepository.findByToken(token);
		authenticationRepository.delete(authentication);
	}
	
	@Transactional
	public void deletePreviousLogin(Customer customer) {
		Authentication authentication = authenticationRepository.findByCustomer(customer);
		if(authentication != null) {
			authenticationRepository.delete(authentication);
		}
	}
	
	@Transactional
	public void validateUser(String token) throws AuthenticationException {
		if(authenticationRepository.findByToken(token) == null) {
			throw new AuthenticationException();
		}
	}
}
