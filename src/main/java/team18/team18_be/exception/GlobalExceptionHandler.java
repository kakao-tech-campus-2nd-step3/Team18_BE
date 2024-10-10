package team18.team18_be.exception;

import java.util.NoSuchElementException;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidException(MethodArgumentNotValidException e) {
    String errorMessage = e.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getDefaultMessage())
        .findFirst()
        .orElse("올바르지 않은 입력 방식입니다.");
    return ResponseEntity.badRequest().body(errorMessage);
  }

  @ExceptionHandler(value = OAuthLoginFailedException.class)
  public ResponseEntity<ExceptionResponse> handleOAuthLoginFailedException(
      OAuthLoginFailedException e) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = JwtInvalidException.class)
  public ResponseEntity<ExceptionResponse> handleJwtInvalidException(
      JwtInvalidException e) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = InternalException.class)
  public ResponseEntity<ExceptionResponse> handleInternalException(InternalException e) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = NoSuchElementException.class)
  public ResponseEntity<ExceptionResponse> handleNoSuchElementException(NoSuchElementException e) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = IllegalArgumentException.class)
  public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException e) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
