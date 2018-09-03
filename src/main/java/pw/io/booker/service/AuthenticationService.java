package pw.io.booker.service;

import pw.io.booker.model.Authentication;
import pw.io.booker.model.Customer;
import pw.io.booker.repo.AuthenticationRepository;
import pw.io.booker.repo.CustomerRepository;

public class AuthenticationService {

	private AuthenticationRepository authenticationRepository;
	private CustomerRepository customerRepository;
	
	public AuthenticationService(AuthenticationRepository authenticationRepository,
			CustomerRepository customerRepository) {
		super();
		this.authenticationRepository = authenticationRepository;
		this.customerRepository = customerRepository;
	}

	public Authentication loginUser(String username, String password) {
		Authentication authentication = new Authentication();
		Customer customer = customerRepository.findByUsername(username);
		return authentication;
	}
}
