package danceschool.javaversion.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SubscriptionDTO {

  private int ID;

  private LocalDateTime StartDate;

  private LocalDateTime NextBillingDate;

  private boolean Canceled;

  private String StudentName;

  private String MembershipName;
}
