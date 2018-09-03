package pw.io.booker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Authentication {

	@Id
	private int authenticationId;
	private String token;
	@OneToOne
	private Customer customer;
	
	public int getAuthenticationId() {
		return authenticationId;
	}
	public void setAuthenticationId(int authenticationId) {
		this.authenticationId = authenticationId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
