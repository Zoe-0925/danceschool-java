package danceschool.javaversion.exception;

import java.lang.RuntimeException;

public class RecordNotFoundException extends RuntimeException {

  public RecordNotFoundException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }
}
