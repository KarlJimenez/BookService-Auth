package pw.io.booker.service;

import java.util.List;

import pw.io.booker.model.Customer;
import pw.io.booker.repo.CustomerRepository;

public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	public List<Customer> findAll() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	public List<Customer> saveAll(List<Customer> customers) {
		for(Customer customer : customers) {
			if(customerRepository.findById(customer.getCustomerId()).isPresent()) {
			throw new RuntimeException("Customers already exist");
			}
		}
		return (List<Customer>) customerRepository.saveAll(customers);
	}
	
	public List<Customer> updateAll(List<Customer> customers) {
		for(Customer customer : customers) {
	    	if(!customerRepository.findById(customer.getCustomerId()).isPresent()) {
	    	  throw new RuntimeException("Customers should exist first");
	      }
	    }
	    return (List<Customer>) customerRepository.saveAll(customers);
	}
	
	public List<Customer> deleteAll(List<Integer> customerIdList) {
		List<Customer> customerList = (List<Customer>) customerRepository.findAllById(customerIdList);
		customerRepository.deleteAll(customerList);
		return customerList;
	}
	
	public Customer getCustomer(int customerId) {
		return customerRepository.findById(customerId).get();
	}
	
	public Customer updateCustomer(int customerId, Customer customer) {
		if(customerId != customer.getCustomerId()) {
	    	throw new RuntimeException("Id is not the same with the object id");
	    }
	    if (!customerRepository.findById(customer.getCustomerId()).isPresent()) {
	    	throw new RuntimeException("Customers should exist first");
	    }
	    customer.setCustomerId(customerId);
	    return customerRepository.save(customer);
	}
	
	public Customer deleteCustomer(int customerId) {
		Customer customer = customerRepository.findById(customerId).get();
	    customerRepository.delete(customer);
	    return customer;
	}
}
