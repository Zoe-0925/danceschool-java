package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.helper.SortDirection;
import danceschool.javaversion.model.Student;
import danceschool.javaversion.repository.StudentRepository;
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
public class StudentService {

  @Autowired
  StudentRepository repository;

  @Cacheable
  public List<Student> getAll() {
    List<Student> classes = new ArrayList<Student>();

    // sort=[field, direction]
    classes.add(new Student(SortDirection.getSortDirection(sort[1]), sort[0]));

    Pageable pagingSort = PageRequest.of(page, size, Sort.by(courses));

    Page<Student> pages = repository
      .findAll(pageReq)
      .stream()
      .map(this::convertToStudentDTO)
      .collect(Collectors.toList());

    return pages.getContent();
  }

  private StudentDTO convertToStudentDTO(Student student) {
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setId(student.getId());
    studentDTO.setUserName(student.getUserName());
    studentDTO.setEmail(student.getEmail());
    studentDTO.setMembership(student.getMembership());

    return studentDTO;
  }

  //TODO
  @Cacheable
  public List<Student> findByName(String name) {}

  //TODO
  @Cacheable
  public Student findByEmail(String email) {}

  //TODO
  @Cacheable
  public String findEmailByBookingId(int id) {}

  @CachePut
  public int create(Student entity) throws Exception {
    try {
      entity = repository.save(entity);
      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  @CachePut
  public void update(Student entity) throws RecordNotFoundException {
    Optional<Student> student = repository.findById(entity.getId());

    if (student.isPresent()) {
      Student newEntity = student.get();
      newEntity.setUserName(entity.getUserName());
      newEntity.setEmail(entity.getEmail());

      newEntity = repository.save(newEntity);
    } else {
      throw new RecordNotFoundException("No Student record exist for given id");
    }
  }

  @CacheEvict(allEntries = true)
  public void deleteStudent(int id) throws RecordNotFoundException {
    Optional<Student> Student = repository.findById(id);

    if (Student.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new RecordNotFoundException("No Student record exist for given id");
    }
  }
}
