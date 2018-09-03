package pw.io.booker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.repo.ImageRepository;
import pw.io.booker.repo.ReservationRepository;
import pw.io.booker.repo.ServiceRepository;
import pw.io.booker.service.CustomerService;
import pw.io.booker.service.ReservationServiceClass;

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
}
