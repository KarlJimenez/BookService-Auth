package pw.io.booker.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import pw.io.booker.exception.AuthenticationException;
import pw.io.booker.service.AuthenticationService;

@Aspect
public class AuthenticationAspect {

	private Logger logger = Logger.getLogger(AuthenticationAspect.class);
	
	private AuthenticationService authenticationService;
	
	public AuthenticationAspect(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@Around("execution(* pw.io.booker.controller..*(..)) && args(.., token)")
	public Object authenticationMethod(ProceedingJoinPoint joinPoint, String token) throws Throwable {
		try {
			authenticationService.validateUser(token);
		} catch (AuthenticationException e) {
			logger.error("Authentication error at " + joinPoint.getSignature().getName());
			throw e;
		} catch (Exception e) {
			logger.error("Authentication error at " + joinPoint.getSignature().getName());
			throw new AuthenticationException("Access denied");
		}
		return joinPoint.proceed();
	}
	
	@Before("execution(* pw.io.booker.service..*(..))")
	public void logBeforeMethod(JoinPoint joinPoint) {
		logger.info("Values before the method - " + joinPoint.getSignature().getName());
	}
	
	@After("execution(* pw.io.booker.service..*(..))")
	public void logAfterMethod(JoinPoint joinPoint) {
		logger.info("Values after the method - " + joinPoint.getSignature().getName());
	}
}
