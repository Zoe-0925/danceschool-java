package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Instructor;
import danceschool.javaversion.repository.InstructorRepository;
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
public class InstructorService {

  @Autowired
  InstructorRepository repository;

  public List<Instructor> getAll() {
    List<Instructor> instructorList = repository.findAll();
    if (instructorList.size() > 0) {
      return instructorList;
    } else {
      return new ArrayList<Instructor>();
    }
  }

  //TODO
  public List<Instructor> findByName(String name) {
    return null;}

  public Long create(Instructor entity) throws Exception {
    try {
      entity = repository.save(entity);
      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  public Instructor update(Instructor entity) throws RecordNotFoundException {
    Optional<Instructor> Instructor = repository.findById(entity.getId());

    if (Instructor.isPresent()) {
      Instructor newEntity = Instructor.get();
      newEntity.setFirstName(entity.getFirstName());
      newEntity.setLastName(entity.getLastName());
      newEntity.setEmail(entity.getEmail());

      newEntity = repository.save(newEntity);

      return newEntity;
    } else {
      /**  throw new RecordNotFoundException(
        "No Instructor record exist for given id"
      );*/
    }
    return entity;
  }

  @CacheEvict(allEntries = true)
  public void deleteInstructor(Long id) throws RecordNotFoundException {
    Optional<Instructor> Instructor = repository.findById(id);

    if (Instructor.isPresent()) {
      repository.deleteById(id);
    } else {
      /**   throw new RecordNotFoundException(
        "No Instructor record exist for given id"
      );*/
    }
  }

public void delete(Long id) {
}
}
