package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.helper.SortDirection;
import danceschool.javaversion.model.Membership;
import danceschool.javaversion.model.Subscription;
import danceschool.javaversion.repository.MembershipRepository;
import danceschool.javaversion.repository.SubscriptionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig
public class SubscriptionService {

  @Autowired
  SubscriptionRepository repository;

  @Autowired
  MembershipRepository membershipRepository;

  @Cacheable
  public List<Subscription> getAll() {
    List<Subscription> classes = new ArrayList<Subscription>();

    // sort=[field, direction]
    classes.add(
      new Subscription(SortDirection.getSortDirection(sort[1]), sort[0])
    );

    Pageable pagingSort = PageRequest.of(page, size, Sort.by(courses));

    Page<Subscription> pages = repository
      .findAll(pageReq)
      .stream()
      .map(this::convertToSubscriptionDTO)
      .collect(Collectors.toList());

    return pages.getContent();
  }

  private SubscriptionDTO convertToSubscriptionDTO(Subscription subscription) {
    SubscriptionDTO SubscriptionDTO = new SubscriptionDTO();
    SubscriptionDTO.setId(subscription.getId());
    SubscriptionDTO.setStartDate(subscription.getStartDate());
    SubscriptionDTO.setCanceled(subscription.getCanceled());
    SubscriptionDTO.setStudentName(subscription.getStudent().getUserName());
    SubscriptionDTO.setMembershipName(subscription.getMembership().getName());

    return SubscriptionDTO;
  }

  @CachePut
  public int create(Subscription entity) throws Exception {
    try {
      entity = repository.save(entity);

      Membership membership = membershipRepository.findById(
        entity.getMembershipID
      );
      membership.getSubscription.add(entity);

      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  @CachePut
  public Boolean update(Subscription entity) {
    Optional<Subscription> subscription = repository.findById(entity.getId());

    if (subscription.isPresent()) {
      Subscription newEntity = subscription.get();
      newEntity.setStartDate(entity.getStartDate());
      newEntity.setNextBillingDate(entity.getNextBillingDate());
      newEntity.setCanceled(entity.getCanceled());
      newEntity.setStudentID(entity.getsetStudentID());
      newEntity.setMembershipName(entity.getMembershipName());

      newEntity = repository.save(newEntity);

      return true;
    } else {
      throw new RecordNotFoundException("No Student record exist for given id");
    }
  }

  @CacheEvict(allEntries = true)
  public void unsubscribe(int id) throws RecordNotFoundException {
    Optional<Subscription> Subscription = repository.findById(id);

    if (Subscription.isPresent()) {
      repository.deleteById(id);
      Membership membership = membershipRepository.findById(
        entity.getMembershipID
      );
      membership.getSubscription.remove(entity);
    } else {
      throw new RecordNotFoundException(
        "No Subscription record exist for given id"
      );
    }
  }
}
