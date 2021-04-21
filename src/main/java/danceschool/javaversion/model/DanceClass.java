package danceschool.javaversion.model;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "DanceClass")
@Data
public class DanceClass {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  private String courseName;

  private int courseID;

  private Course course;

  private ArrayList<Booking> bookings;
}
