package pw.io.booker.controller;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pw.io.booker.model.Customer;
import pw.io.booker.service.CustomerService;

@RestController
@Transactional
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerRepository) {
		super();
		this.customerService = customerRepository;
	}

	@GetMapping
	public List<Customer> getAll(@RequestHeader("Authentication-token") String token) {
		return customerService.findAll();
	}

	@PostMapping
	public List<Customer> saveAll(@RequestBody List<Customer> customers) {
		return customerService.saveAll(customers);
	}

	@PutMapping
	public List<Customer> updateAll(@RequestBody List<Customer> customers, @RequestHeader("Authentication-token") String token) {
		return customerService.updateAll(customers);
	}

	@DeleteMapping
	public List<Customer> deleteAll(@RequestParam("customerIdList") List<Integer> customerIdList, @RequestHeader("Authentication-token") String token) {
		return customerService.deleteAll(customerIdList);
	}

	@GetMapping("/{customerId}")
	public Customer getCustomer(@PathVariable("customerId") int customerId, @RequestHeader("Authentication-token") String token) {
		return customerService.getCustomer(customerId);
	}

	@PutMapping("/{customerId}")
	public Customer updateCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer customer, @RequestHeader("Authentication-token") String token) {
		return customerService.updateCustomer(customerId, customer);
	}

	@DeleteMapping("/{customerId}")
	public Customer deleteCustomer(@PathVariable("customerId") int customerId, @RequestHeader("Authentication-token") String token) {
		return customerService.deleteCustomer(customerId);
	}
}
