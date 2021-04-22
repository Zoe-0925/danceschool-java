package danceschool.javaversion.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Instructor {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotNull
  @Size(max = 30)
  private String firstName;

  @Size(max = 30)
  private String lastName;

  @NotNull
  @Size(max = 50)
  private String email;
}
