package danceschool.javaversion.model;


import lombok.Data;

@Data
public class SignUpRequest {

  private String email;

  private String password;

  private String userName;
}
