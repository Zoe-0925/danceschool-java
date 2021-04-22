package danceschool.javaversion.model;

import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Table(name = "Student")
@Data
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotNull
  @Size(max = 50)
  private String userName;

  @NotNull
  @Size(max = 40)
  private String email;

  @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
  Set subscription = new HashSet();

  @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
  Set bookings = new HashSet();
}
