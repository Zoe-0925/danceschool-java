package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Instructor;
import danceschool.javaversion.repository.InstructorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Instructorervice {

  @Autowired
  InstructorRepository repository;

  public List<Instructor> findAllInstructors() {
    List<Instructor> instructorList = repository.findAll();

    //TODO DTO

    if (instructorList.size() > 0) {
      return InstructorList;
    } else {
      return new ArrayList<Instructor>();
    }
  }

  //TODO
  public List<Instructor> findByName(String name) {}

  public int create(Instructor entity) {
    Instructor created = repository.save(entity);

    return created.getId();
  }

  public Instructor Update(Instructor entity) throws RecordNotFoundException {
    Optional<Instructor> Instructor = repository.findById(entity.getId());

    if (Instructor.isPresent()) {
      Instructor newEntity = Instructor.get();
      newEntity.setFirstName(entity.getFirstName());
      newEntity.setLastName(entity.getLastName());
      newEntity.setEmail(entity.getEmail());

      newEntity = repository.save(newEntity);

      return newEntity;
    } else {
      return null;
    }
  }

  public void deleteInstructor(int id) throws RecordNotFoundException {
    Optional<Instructor> Instructor = repository.findById(id);

    if (Instructor.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new RecordNotFoundException(
        "No Instructor record exist for given id"
      );
    }
  }
}
