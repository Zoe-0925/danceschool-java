package danceschool.javaversion.model;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Table(name = "Booking")
@Data
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotNull
  private LocalDateTime bookingDate;

  @NotNull
  private int studentID;

  @NotNull
  private int instructorID;

  private int membershipID;

  @NotNull
  private int classID;

  private DanceClass danceClass;

  private Membership membership;

  private Student student;
}
