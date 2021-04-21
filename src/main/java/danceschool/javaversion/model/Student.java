package danceschool.javaversion.model;

import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "Student")
@Data
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String userName;

  private String email;

  private ArrayList<Subscription> subscription;

  private ArrayList<Booking> bookings;
}
