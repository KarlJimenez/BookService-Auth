package pw.io.booker.exception;

public class AuthenticationException extends RuntimeException {
	
	private final String message;
	
	public AuthenticationException(String message) {
		this.message = message;
	}
	
	public String getUserFriendlyMessage() {
		return message;
	}
}
