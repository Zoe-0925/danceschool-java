package danceschool.javaversion.dto;

import lombok.Data;

@Data
public class UserRecordArgs {

  private String email;
  private boolean emailVerified;
  private String password;
  private String displayName;
  private boolean disabled;
}
