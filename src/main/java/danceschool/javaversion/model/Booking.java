package danceschool.javaversion.model;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Table(name = "Booking")
@Data
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private LocalDateTime bookingDate;

  private int studentID;

  private int instructorID;

  private int membershipID;

  private int classID;

  private DanceClass danceClass;

  private Membership membership;

  private Student student;
}
