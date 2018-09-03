package pw.io.booker.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import pw.io.booker.exception.AuthenticationException;
import pw.io.booker.service.AuthenticationService;

@Aspect
@Component
public class AuthenticationAspect {

	private AuthenticationService authenticationService;
	
	public AuthenticationAspect(pw.io.booker.service.AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@Around("execution(* pw.io.booker.controller..*(..)) && args(.., token)")
	public Object authenticationMethod(ProceedingJoinPoint joinPoint, String token) throws Throwable {
		try {
			authenticationService.validateUser(token);
		} catch (AuthenticationException e) {
			throw e;
		} catch (Exception e) {
			throw new AuthenticationException("Access denied");
		}
		return joinPoint.proceed();
	}
}
