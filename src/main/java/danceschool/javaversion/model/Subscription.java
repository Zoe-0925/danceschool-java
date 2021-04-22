package danceschool.javaversion.model;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "Subscription")
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int ID;

  @NotNull
  private LocalDateTime StartDate;

  @NotNull
  private LocalDateTime NextBillingDate;

  private boolean Canceled;

  @NotNull
  private int StudentID;

  @NotNull
  private int MembershipID;

  @NotNull
  private String MembershipName;

  private Student Student;

  private Membership Membership;
}
