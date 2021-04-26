package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository
  extends PagingAndSortingRepository<Subscription, Long> {
  @Query("select Count(*) from Subscription")
  int getCount();
}
