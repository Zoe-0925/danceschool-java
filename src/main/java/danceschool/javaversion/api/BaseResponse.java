package danceschool.javaversion.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BaseResponse {

  private ApiError Error;
  private boolean Success;
  private T Data;
  private boolean IsCached;

  public BaseResponse(ApiError error) {
    this.Data = null;
    this.Success = false;
    this.Error = error;
    this.IsCached = false;
  }

  public BaseResponse(T data) {
    this.Data = data;
    this.Success = true;
    this.Error = null;
    this.IsCached = false;
  }

  public BaseResponse(T data, bool isCached) {
    this.Data = data;
    this.Success = true;
    this.Error = null;
    this.IsCached = isCached;
  }
}
