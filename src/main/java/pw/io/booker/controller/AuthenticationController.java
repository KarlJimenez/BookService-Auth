package pw.io.booker.controller;

import javax.security.sasl.AuthenticationException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pw.io.booker.model.Authentication;
import pw.io.booker.model.User;
import pw.io.booker.service.AuthenticationService;

@RestController
@RequestMapping("/main")
public class AuthenticationController {

	private AuthenticationService authenticationService;

	public AuthenticationController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody User user) throws AuthenticationException {
		Authentication authentication = authenticationService.loginUser(user);
		return authentication.getToken();
	}
	
	@DeleteMapping("/logout")
	public void logoutUser(@RequestHeader("Authentication-token") String token) {
		authenticationService.logoutUser(token);
	}
}
