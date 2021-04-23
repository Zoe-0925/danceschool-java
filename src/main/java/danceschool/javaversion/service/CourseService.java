package danceschool.javaversion.service;

import danceschool.javaversion.dto.CourseDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.helper.SortDirection;
import danceschool.javaversion.model.Course;
import danceschool.javaversion.repository.CourseRepository;
import java.util.ArrayList;
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
@CacheConfig(cacheNames = { "_courses_" }) // tells Spring where to store
public class CourseService {

  @Autowired
  CourseRepository repository;

  @Cacheable
  public List<CourseDTO> getAll() {
    List<Course> courses = new ArrayList<Course>();

    // sort=[field, direction]
    courses.add(new Course(SortDirection.getSortDirection(sort[1]), sort[0]));

    Pageable pagingSort = PageRequest.of(page, size, Sort.by(courses));

    Page<Course> result = repository
      .findAll(pageReq)
      .getContent()
      .stream()
      .map(this::convertToCourseDTO)
      .collect(Collectors.toList());

    return result;
  }

  private CourseDTO convertToCourseDTO(Course course) {
    CourseDTO courseDTO = new CourseDTO();
    courseDTO.setId(course.getId());
    courseDTO.setName(course.getName());
    courseDTO.setPrice(course.getPrice());
    courseDTO.setBookingLimit(course.getBookingLimit());
    courseDTO.setClassCount(course.getDanceClasses().length());
    courseDTO.setInstructorID(course.getInstructorID());

    return courseDTO;
  }

  //TODO
  @Cacheable
  public List<Course> findByName(String name) {}

  @Cacheable
  public CourseWithCountDTO findWithCount() {
    List<Course> CourseList = repository.findAll();

    if (CourseList.size() > 0) {
      return new CourseWithCountDTO(CourseList, CourseList.length());
    } else {
      return new CourseWithCountDTO(new ArrayList<Course>(), 0);
    }
  }

  @CachePut
  public int create(Course entity) throws Exception {
    try {
      entity = repository.save(entity);
      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  @CachePut
  public Course Update(Course entity) throws RecordNotFoundException {
    Optional<Course> Course = repository.findById(entity.getId());

    if (Course.isPresent()) {
      Course newEntity = Course.get();
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

  @CacheEvict(allEntries = true)
  public void delete(int id) throws RecordNotFoundException {
    Optional<Course> Course = repository.findById(id);

    if (Course.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new RecordNotFoundException("No Course record exist for given id");
    }
  }
}
