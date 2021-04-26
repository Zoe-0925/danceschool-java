package danceschool.javaversion.service;

import danceschool.javaversion.dto.CourseDTO;
import danceschool.javaversion.dto.CourseWithCountDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Course;
import danceschool.javaversion.repository.CourseRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
@CacheConfig(cacheNames = { "_courses_" }) // tells Spring where to store
public class CourseService {

  @Autowired
  CourseRepository repository;

  @Cacheable // caches the result of findAll() method
  public List<CourseDTO> getAll(int page, int pageSize) {
    Pageable paging = PageRequest.of(page, pageSize, Sort.by("name"));
    List<Course> courses = repository.findAll(paging).getContent();
    return listToDTO(courses);
  }

  private CourseDTO convertToCourseDTO(Course course) {
    return new CourseDTO(course);
  }

  private List<CourseDTO> iterableToDTO(Iterable<Course> courses) {
    return StreamSupport
      .stream(courses.spliterator(), false)
      .map(this::convertToCourseDTO)
      .collect(Collectors.toList());
  }

  private List<CourseDTO> listToDTO(List<Course> courses) {
    return courses
      .stream()
      .map(this::convertToCourseDTO)
      .collect(Collectors.toList());
  }

  @Cacheable
  public int getTotal() {
    return repository.getCount();
  }

  @Cacheable
  public List<CourseDTO> findByName(String name) {
    List<Course> courses = repository.findByName(name);
    return listToDTO(courses);
  }

  @Cacheable
  public CourseWithCountDTO findWithCount() {
    Iterable<Course> courses = repository.findAll();
    List<CourseDTO> courseDTOList = iterableToDTO(courses);
    return new CourseWithCountDTO(courseDTOList, courseDTOList.size());
  }
  
  @CachePut
  public Long create(Course entity) throws Exception {
    try {
      entity = repository.save(entity);
      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  @CachePut
  public boolean update(Course course) throws RecordNotFoundException {
    Optional<Course> entity = repository.findById(course.getId());

    if (entity.isPresent()) {
      Course newEntity = entity.get();
      newEntity.setName(course.getName());
      newEntity.setPrice(course.getPrice());
      newEntity.setInstructorID(course.getInstructorID());
      newEntity.setBookingLimit(course.getBookingLimit());
      newEntity = repository.save(newEntity);
      return true;
    } else {
      return false;
    }
  }

  @CacheEvict(allEntries = true)
  public void delete(Long id) throws RecordNotFoundException {
    Optional<Course> Course = repository.findById(id);

    if (Course.isPresent()) {
      repository.deleteById(id);
    } else {
      // throw new RecordNotFoundException("No Course record exist for given id");
    }
  }
}
