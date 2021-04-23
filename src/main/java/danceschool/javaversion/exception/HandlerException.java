package danceschool.javaversion.exception;

import danceschool.javaversion.api.apiErrors.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class HandlerException {

  @ExceptionHandler(Exception.class)
  ResponseEntity<ApiError> exceptionHandler(Exception e) {


    return ResponseEntity
      .badRequest()
      .body(
        ApiError
          .builder()
          .status(HttpStatus.BAD_REQUEST.value())
          .statusDescription(
            e.getClass().getSimpleName() + ".class Unexpected Error"
          )
          .message(e.getMessage())
          .build()
      );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ApiError> methodArgumentNotValidException(
    MethodArgumentNotValidException e
  ) {


    return ResponseEntity
      .badRequest()
      .body(
        ApiError
          .builder()
          .status(HttpStatus.BAD_REQUEST.value())
          .statusDescription("Arguments not valid")
          .build()
      );
  }
}
