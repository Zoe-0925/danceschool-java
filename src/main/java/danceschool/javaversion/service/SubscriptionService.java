package danceschool.javaversion.service;

import danceschool.javaversion.dto.SubscriptionDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Membership;
import danceschool.javaversion.model.Subscription;
import danceschool.javaversion.repository.MembershipRepository;
import danceschool.javaversion.repository.SubscriptionRepository;
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
  public List<SubscriptionDTO> getAll(int page, int pageSize) {
    Pageable paging = PageRequest.of(
      page,
      pageSize,
      Sort.by("bookingDate").descending()
    );

    return repository
      .findAll(paging)
      .getContent()
      .stream()
      .map(this::convertToSubscriptionDTO)
      .collect(Collectors.toList());
  }

  private SubscriptionDTO convertToSubscriptionDTO(Subscription subscription) {
    return new SubscriptionDTO(subscription);
  }

  @Cacheable
  public int getTotal() {
    return repository.getCount();
  }

  @CachePut
  public Long create(Subscription entity) {
    entity = repository.save(entity);

    Optional<Membership> membership = membershipRepository.findById(
      entity.getMembershipID()
    );
    membership.get().getSubscription().add(entity);
    return entity.getId();
  }

  @CachePut
  public Boolean update(Subscription entity) throws RecordNotFoundException {
    Optional<Subscription> subscription = repository.findById(entity.getId());
    if (subscription.isPresent()) {
      Subscription newEntity = subscription.get();
      newEntity.setStartDate(entity.getStartDate());
      newEntity.setNextBillingDate(entity.getNextBillingDate());
      newEntity.setCanceled(entity.getCanceled());
      newEntity.setStudentID(entity.getStudentID());
      newEntity.setMembershipName(entity.getMembershipName());

      newEntity = repository.save(newEntity);

      return true;
    } else {
      return false;
    }
  }

  @CacheEvict(allEntries = true)
  public boolean unsubscribe(Long id) throws RecordNotFoundException {
    Optional<Subscription> entity = repository.findById(id);

    if (entity != null) {
      repository.deleteById(id);
      Optional<Membership> membership = membershipRepository.findById(
        entity.get().getMembershipID()
      );
      membership.get().getSubscription().remove(membership);
      return true;
    } else {
      return false;
    }
  }
}
