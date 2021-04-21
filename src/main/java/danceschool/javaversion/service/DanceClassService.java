package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.DanceClass;
import danceschool.javaversion.repository.DanceClassRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DanceClasservice {

  @Autowired
  DanceClassRepository repository;

  public List<DanceClass> findAllDanceClasses() {
    List<DanceClass> danceClassList = repository.findAll();

    //TODO DTO

    if (danceClassList.size() > 0) {
      return danceClassList;
    } else {
      return new ArrayList<DanceClass>();
    }
  }

  //TODO
  public List<DanceClass> findByCourse(int id) {}

  public int create(DanceClass entity) {
    DanceClass created = repository.save(entity);

    return created.getId();
  }

  public DanceClass Update(DanceClass entity)
    throws RecordNotFoundException {
    Optional<DanceClass> DanceClass = repository.findById(entity.getId());

    if (DanceClass.isPresent()) {
      DanceClass newEntity = DanceClass.get();
      newEntity.setName(entity.getName());
      newEntity.setPrice(entity.getPrice());
      newEntity.setInstructorID(entity.getInstructorID());
      newEntity.setBookingLimit(entity.getBookingLimit());

      newEntity = repository.save(newEntity);

      return newEntity;
    } else {
      return null;
    }
  }

  public void delete(int id) throws RecordNotFoundException {
    Optional<DanceClass> DanceClass = repository.findById(id);

    if (DanceClass.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new RecordNotFoundException(
        "No DanceClass record exist for given id"
      );
    }
  }
}