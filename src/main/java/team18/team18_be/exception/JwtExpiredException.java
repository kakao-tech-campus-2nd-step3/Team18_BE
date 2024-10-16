package team18.team18_be.exception;

public class JwtExpiredException extends RuntimeException {

  public JwtExpiredException() {
  }

  public JwtExpiredException(String message) {
    super(message);
  }
}
