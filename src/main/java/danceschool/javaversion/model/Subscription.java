package danceschool.javaversion.model;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table(name = "Subscription")

public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private LocalDateTime startDate;

  @NotNull
  private LocalDateTime nextBillingDate;

  private boolean canceled;

  @NotNull
  private Long studentID;

  @NotNull
  private Long membershipID;

  @NotNull
  private String membershipName;

  @ManyToOne
  @JoinColumn(name = "studentID")
  private Student student;

  @ManyToOne
  @JoinColumn(name = "membershipID")
  private Membership membership;
}
