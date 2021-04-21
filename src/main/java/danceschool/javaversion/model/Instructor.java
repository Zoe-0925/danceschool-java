package danceschool.javaversion.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
public class Instructor {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String firstName;

  private String lastName;

  private String email;
}
