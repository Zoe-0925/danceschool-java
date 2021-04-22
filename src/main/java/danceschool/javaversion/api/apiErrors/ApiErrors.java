package danceschool.javaversion.api.apiErrors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ApiError {

  private int statusCode;

  public String statusDescription;

  public String message;


  public ApiError(int statusCode, String statusDescription) {
    this.statusCode = statusCode;
    this.statusDescription = statusDescription;
    this.message = "";
  }

  public ApiError(int statusCode, String statusDescription, String message) {
    this.message = message;
    this.statusCode = statusCode;
    this.statusDescription = statusDescription;
  }
}
