package team18.team18_be.exception;

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
}
