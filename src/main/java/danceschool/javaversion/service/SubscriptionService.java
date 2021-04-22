package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.helper.SortDirection;
import danceschool.javaversion.model.Subscription;
import danceschool.javaversion.repository.SubscriptionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

  @Autowired
  SubscriptionRepository repository;

  public List<Subscription> getAll() {
    List<Subscription> classes = new ArrayList<Subscription>();

    // sort=[field, direction]
    classes.add(
      new Subscription(SortDirection.getSortDirection(sort[1]), sort[0])
    );

    Pageable pagingSort = PageRequest.of(page, size, Sort.by(courses));

    Page<Subscription> pages = repository.findAll(pageReq);

    //TODO DTO

    return pages.getContent();
  }

  public Subscription createOrUpdateSubscription(Subscription entity)
    throws RecordNotFoundException {
    Optional<Subscription> subscription = repository.findById(entity.getId());

    if (subscription.isPresent()) {
      Subscription newEntity = subscription.get();
      newEntity.setStartDate(entity.getStartDate());
      newEntity.setNextBillingDate(entity.getNextBillingDate());
      newEntity.setCanceled(entity.getCanceled());
      newEntity.setStudentID(entity.getsetStudentID());
      newEntity.setMembershipName(entity.getMembershipName());

      newEntity = repository.save(newEntity);

      return newEntity;
    } else {
      entity = repository.save(entity);

      return entity;
    }
  }

  public void unsubscribe(int id) throws RecordNotFoundException {
    Optional<Subscription> Subscription = repository.findById(id);

    if (Subscription.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new RecordNotFoundException(
        "No Subscription record exist for given id"
      );
    }
  }
}
