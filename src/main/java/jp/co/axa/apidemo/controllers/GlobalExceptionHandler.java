package jp.co.axa.apidemo.controllers;

import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Not Found Exception
   * @param ex
   * @return
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handle(EntityNotFoundException ex) {
    String errorMsg = "Employee ID not exists";
    log.info(errorMsg );
    return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
  }

  /**
   * Unknown Error
   * @param ex
   * @return
   */
  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<String> handle(Throwable ex) {

    log.error("Unknown error", ex);

    return new ResponseEntity<>(
        "Unknown error: " + ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}