package danceschool.javaversion.dto;

import danceschool.javaversion.model.Subscription;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SubscriptionDTO {

  private Long id;

  private LocalDateTime startDate;

  private LocalDateTime nextBillingDate;

  private boolean canceled;

  private String studentName;

  private String membershipName;

  public SubscriptionDTO(Subscription subscription) {
    this.id = subscription.getId();
    this.startDate = subscription.getStartDate();
    this.nextBillingDate = subscription.getNextBillingDate();
    this.canceled = subscription.getCanceled();
    this.studentName = subscription.getStudent().getUserName();
    this.membershipName = subscription.getMembership().getName();
  }
}
