package team18.team18_be.exception;

public class FileDownloadException extends RuntimeException {

  public FileDownloadException() {
  }

  public FileDownloadException(String message) {
    super(message);
  }
}
