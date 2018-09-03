package pw.io.booker.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pw.io.booker.model.Authentication;
import pw.io.booker.model.Customer;

@Repository
public interface AuthenticationRepository extends CrudRepository<Authentication, Integer> {

	public Authentication findByToken(String token);
	public Authentication findByCustomer(Customer customer);
}
