package danceschool.javaversion.model;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import javax.persistence.Table;

@Data
@Table(name="Subscription")
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int ID;

  private LocalDateTime StartDate;

  private LocalDateTime NextBillingDate;

  private boolean Canceled;

  private int StudentID;

  private int MembershipID;

  private String MembershipName;

  private Student Student;

  private Membership Membership;
}

