package danceschool.javaversion.model;

import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

  private ArrayList<Subscription> subscription;

  private ArrayList<Booking> bookings;
}
