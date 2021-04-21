package danceschool.javaversion.service;

import danceschool.javaversion.model.Membership;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.repository.MembershipRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {

  @Autowired
  MembershipRepository repository;

  public List<Membership> findAllMemberships() {
    List<Membership> MembershipList = repository.findAll();
    //TODO: How to find and map into DTO????

    if (MembershipList.size() > 0) {
      return MembershipList;
    } else {
      return new ArrayList<Membership>();
    }
  }

  public Membership createOrUpdateMembership(Membership entity)
    throws RecordNotFoundException {
    Optional<Membership> membership = repository.findById(entity.getId());

    if (membership.isPresent()) {
      Membership newEntity = Membership.get();
      newEntity.setName(entity.getName());
      newEntity.setDuration(entity.getDuration());
      newEntity.setCanceled(entity.getCanceled());
      newEntity.setPrice(entity.getPrice());

      newEntity = repository.save(newEntity);

      return newEntity;
    } else {
      entity = repository.save(entity);

      return entity;
    }
  }

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
