package team18.team18_be.exception;

public class JwtInvalidException extends RuntimeException {

  public JwtInvalidException() {
  }

  public JwtInvalidException(String message) {
    super(message);
  }
}
