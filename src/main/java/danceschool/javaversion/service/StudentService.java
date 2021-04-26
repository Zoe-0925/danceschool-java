package danceschool.javaversion.service;

import danceschool.javaversion.dto.StudentDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Student;
import danceschool.javaversion.repository.StudentRepository;
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
public class StudentService {

  @Autowired
  StudentRepository repository;

  @Cacheable // caches the result of findAll() method
  public List<StudentDTO> getAll(int page, int pageSize) {
    Pageable paging = PageRequest.of(page, pageSize, Sort.by("userName"));

    List<StudentDTO> StudentList = repository
      .findAll(paging)
      .getContent()
      .stream()
      .map(this::convertToStudentDTO)
      .collect(Collectors.toList());

    return StudentList;
  }

  private StudentDTO convertToStudentDTO(Student student) {
    return new StudentDTO(student);
  }

  //TODO
  @Cacheable
  public List<Student> findByName(String query) {
    return repository.findByName(query);
  }

  @Cacheable
  public Student findByEmail(String query) {
    return repository.findByEmail(query).get();
  }

  //TODO
  @Cacheable
  public String findEmailByBookingId(Long id) {
    return null;
  }

  @CachePut
  public Long create(Student entity) throws Exception {
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
      // throw new RecordNotFoundException("No Student record exist for given id");
    }
  }

  @CacheEvict(allEntries = true)
  public void deleteStudent(Long id) throws RecordNotFoundException {
    Optional<Student> Student = repository.findById(id);

    if (Student.isPresent()) {
      repository.deleteById(id);
    } else {
      // throw new RecordNotFoundException("No Student record exist for given id");
    }
  }

  public void delete(Long id) {}
}
