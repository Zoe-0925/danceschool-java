package danceschool.javaversion.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SubscriptionDTO {

  private int id;

  private LocalDateTime startDate;

  private LocalDateTime nextBillingDate;

  private boolean canceled;

  private String studentName;

  private String membershipName;
}
