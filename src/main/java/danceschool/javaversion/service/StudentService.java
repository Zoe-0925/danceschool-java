package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Student;
import danceschool.javaversion.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired
  StudentRepository repository;

  public List<Student> getAll() {
    List<Student> classes = new ArrayList<Student>();

    // sort=[field, direction]
    classes.add(new Student(getSortDirection(sort[1]), sort[0]));

    Pageable pagingSort = PageRequest.of(page, size, Sort.by(courses));

    Page<Student> pages = repository.findAll(pageReq);

    //TODO DTO

    return pages.getContent();
  }

  //TODO
  public List<Student> findByName(String name) {}

  //TODO
  public Student findByEmail(String email) {}

  //TODO
  public String findEmailByBookingId(int id) {}

  public Student createOrUpdateStudent(Student entity)
    throws RecordNotFoundException {
    Optional<Student> student = repository.findById(entity.getId());

    if (student.isPresent()) {
      Student newEntity = student.get();
      newEntity.setUserName(entity.getUserName());
      newEntity.setEmail(entity.getEmail());

      newEntity = repository.save(newEntity);

      return newEntity;
    } else {
      entity = repository.save(entity);

      return entity;
    }
  }

  public void deleteStudent(int id) throws RecordNotFoundException {
    Optional<Student> Student = repository.findById(id);

    if (Student.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new RecordNotFoundException("No Student record exist for given id");
    }
  }
}
