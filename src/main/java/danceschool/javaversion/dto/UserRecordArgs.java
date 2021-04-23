package danceschool.javaversion.dto;

import lombok.Data;

@Data
public class UserRecordArgs {

  private String email;
  private boolean emailVerified;
  private String password;
  private String displayName;
  private boolean disabled;

  public UserRecordArgs(String email, String password, String displayName) {
    this.email = email;
    this.password = password;
    this.displayName = displayName;
    this.emailVerified = true;
    this.disabled = false;
  }
}
