package team18.team18_be.exception;

public class OAuthLoginFailedException extends RuntimeException {

  public OAuthLoginFailedException() {
  }

  public OAuthLoginFailedException(String message) {
    super(message);
  }
}
