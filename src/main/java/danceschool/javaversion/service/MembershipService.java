package danceschool.javaversion.service;

import danceschool.javaversion.dto.MembershipDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Membership;
import danceschool.javaversion.repository.MembershipRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig
public class MembershipService {

  @Autowired
  MembershipRepository repository;

  @Cacheable
  public List<Membership> getAll() {
    List<Membership> membershipList = repository
      .findAll()
      .stream()
      .map(this::convertToMembershipDTO)
      .collect(Collectors.toList());

    return membershipList;
  }

  private MembershipDTO convertToMembershipDTO(Membership membership) {
    MembershipDTO membershipDTO = new MembershipDTO();
    membershipDTO.setId(membership.getId());
    membershipDTO.setName(membership.getName());
    membershipDTO.setCount(membership.getSubscription().size());

    return membershipDTO;
  }

  @CachePut
  public Long create(Membership entity) throws Exception {
    try {
      entity = repository.save(entity);
      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  @CachePut
  public void update(Membership entity) throws RecordNotFoundException {
    Optional<Membership> membership = repository.findById(entity.getId());

    if (membership.isPresent()) {
      Membership newEntity = membership.get();
      newEntity.setName(entity.getName());
      newEntity.setDuration(entity.getDuration());
      newEntity.setPrice(entity.getPrice());

      newEntity = repository.save(newEntity);
    } else {
    /**   throw new RecordNotFoundException(
        "No Membership record exist for given id"
      );*/
    }
  }

  @CacheEvict(allEntries = true)
  public void deleteMembership(Long id) throws RecordNotFoundException {
    Optional<Membership> Membership = repository.findById(id);

    if (Membership.isPresent()) {
      repository.deleteById(id);
    } else {
     /** throw new RecordNotFoundException(
        "No Membership record exist for given id"
      );*/
    }
  }
}
