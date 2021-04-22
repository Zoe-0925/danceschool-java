package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Membership;
import danceschool.javaversion.repository.MembershipRepository;
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
public class MembershipService {

  @Autowired
  MembershipRepository repository;

  @Cacheable
  public List<Membership> findAllMemberships() {
    List<Membership> MembershipList = repository.findAll();
    //TODO: How to find and map into DTO????

    if (MembershipList.size() > 0) {
      return MembershipList;
    } else {
      return new ArrayList<Membership>();
    }
  }

  @CachePut
  public int create(Membership entity) throws Exception {
    try{
      entity = repository.save(entity);
      return entity.getId();
    }catch(Exception e){
      throw e;
    }
  }

  @CachePut
  public void update(Membership entity) throws RecordNotFoundException {
    Optional<Membership> membership = repository.findById(entity.getId());

    if (membership.isPresent()) {
      Membership newEntity = Membership.get();
      newEntity.setName(entity.getName());
      newEntity.setDuration(entity.getDuration());
      newEntity.setCanceled(entity.getCanceled());
      newEntity.setPrice(entity.getPrice());

      newEntity = repository.save(newEntity);
    } else {
      throw new RecordNotFoundException(
        "No Membership record exist for given id"
      );
    }
  }

  @CacheEvict(allEntries = true)
  public void deleteMembership(int id) throws RecordNotFoundException {
    Optional<Membership> Membership = repository.findById(id);

    if (Membership.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new RecordNotFoundException(
        "No Membership record exist for given id"
      );
    }
  }
}
