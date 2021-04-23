package danceschool.javaversion.service;

import danceschool.javaversion.dto.SubscriptionDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.helper.SortDirection;
import danceschool.javaversion.model.Membership;
import danceschool.javaversion.model.Subscription;
import danceschool.javaversion.repository.MembershipRepository;
import danceschool.javaversion.repository.SubscriptionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@CacheConfig
public class SubscriptionService {

  @Autowired
  SubscriptionRepository repository;

  @Autowired
  MembershipRepository membershipRepository;

  @Cacheable
  public List<Subscription> getAll(int page, int size, String[] sort) {
    List<Subscription> classes = new ArrayList<Subscription>();

    // sort=[field, direction]
    classes.add(
      new Subscription(SortDirection.getSortDirection(sort[1]), sort[0])
    );

    //TODO bug
    //TODO specify page sort
    Pageable pagingSort = PageRequest.of(page, size, Sort.by(courses));

    List<SubscriptionDTO> pages = repository
      .findAll(pageReq)
      .stream()
      .map(this::convertToSubscriptionDTO)
      .collect(Collectors.toList());

    return pages.getContent();
  }

  private SubscriptionDTO convertToSubscriptionDTO(Subscription subscription) {
    return new SubscriptionDTO(subscription);
  }

  @CachePut
  public Long create(Subscription entity) throws Exception {
    try {
      entity = repository.save(entity);

      Membership membership = membershipRepository.findById(
        entity.getMembershipID()
      );
      membership.getSubscription().add(entity);

      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  @CachePut
  public Boolean update(Subscription entity) throws RecordNotFoundException {
    Optional<Subscription> subscription = repository.findById(entity.getId());

    //TODO
    //wrong
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
      /**     throw new RecordNotFoundException(
        "No Subscription record exist for given id", RuntimeException e
      );*/
    }
  }

  @CacheEvict(allEntries = true)
  public void unsubscribe(Long id) throws RecordNotFoundException {
    Subscription entity = repository.findById(id);

    if (entity != null) {
      repository.deleteById(id);
      Membership membership = membershipRepository.findById(
        entity.getMembershipID()
      );
      membership.getSubscription().remove(membership);
    } else {
      /**     throw new RecordNotFoundException(
        "No Subscription record exist for given id", RuntimeException e
      );*/
    }
  }
}
