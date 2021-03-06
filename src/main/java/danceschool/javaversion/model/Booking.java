package danceschool.javaversion.model;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Booking")
@Data
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private LocalDateTime bookingDate;

  @NotNull
  private Long studentID;

  @NotNull
  private Long instructorID;

  private Long membershipID;

  @NotNull
  private Long classID;

  @ManyToOne
  @JoinColumn(name = "classID")
  private DanceClass danceClass;

  @ManyToOne
  @JoinColumn(name = "membershipID")
  private Membership membership;

  @ManyToOne
  @JoinColumn(name = "studentID")
  private Student student;
}
