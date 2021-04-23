package danceschool.javaversion.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Table(name = "Student")
@Data
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(max = 50)
  private String userName;

  @NotNull
  @Size(max = 40)
  private String email;

  @OneToOne(mappedBy = "subscription", cascade = CascadeType.ALL)
  Subscription subscription;

  @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
  Set bookings = new HashSet();

  public Student(String userName, String email) {
    this.userName = userName;
    this.email = email;
  }
}
