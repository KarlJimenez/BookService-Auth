package pw.io.booker.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import pw.io.booker.exception.AuthenticationException;

@Aspect
@Component
public class AuthenticationAspect {

	@Around("execution(* pw.io.booker.service..*(..) && args(token)")
	public Object authenticationMethod(ProceedingJoinPoint joinPoint, String token) {
		try {
			joinPoint.proceed();
		} catch (AuthenticationException e) {
			e.getUserFriendlyMessage();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			return joinPoint;
		}
	}
}
