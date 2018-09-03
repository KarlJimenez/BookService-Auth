package pw.io.booker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pw.io.booker.repo.AuthenticationRepository;
import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.repo.ImageRepository;
import pw.io.booker.repo.ReservationRepository;
import pw.io.booker.repo.ServiceRepository;
import pw.io.booker.service.AuthenticationService;
import pw.io.booker.service.CustomerService;
import pw.io.booker.service.ReservationServiceClass;
import pw.io.booker.util.TokenCreator;

@Configuration
public class BookerAppConfig {

	@Bean
	public CustomerService customerService(CustomerRepository customerRepository) {
		return new CustomerService(customerRepository);
	}
	
	@Bean
	public ReservationServiceClass reservationService(ReservationRepository reservationRepository, 
			ServiceRepository serviceRepository, ImageRepository imageRepository, CustomerRepository customerRepository) {
		return new ReservationServiceClass(reservationRepository, serviceRepository, imageRepository, customerRepository);
	}
	
	@Bean
	public AuthenticationService authenticationService(AuthenticationRepository authenticationRepository, 
			CustomerRepository customerRepository, TokenCreator tokenCreator) {
		return new AuthenticationService(authenticationRepository, customerRepository, tokenCreator);
	}
	
	@Bean
	public TokenCreator tokenCreator() {
		return new TokenCreator();
	}
}
