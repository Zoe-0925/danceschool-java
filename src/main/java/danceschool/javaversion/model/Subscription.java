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
import lombok.Data;

@Data
@Table(name = "Subscription")
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotNull
  private LocalDateTime startDate;

  @NotNull
  private LocalDateTime nextBillingDate;

  private boolean canceled;

  @NotNull
  private int studentID;

  @NotNull
  private int membershipID;

  @NotNull
  private String membershipName;

  @ManyToOne
  @JoinColumn(name = "studentID")
  private Student student;

  @ManyToOne
  @JoinColumn(name = "membershipID")
  private Membership membership;
}
