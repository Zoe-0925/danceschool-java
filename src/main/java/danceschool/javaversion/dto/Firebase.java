package danceschool.javaversion.dto;


import lombok.Data;

@Data
public class Firebase {

  private String type;
  private String project_id;
  private String client_email;
  private String client_id;
  private String private_key;
}
