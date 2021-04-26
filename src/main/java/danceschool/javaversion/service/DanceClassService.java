package danceschool.javaversion.service;

import danceschool.javaversion.dto.DanceClassDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Course;
import danceschool.javaversion.model.DanceClass;
import danceschool.javaversion.repository.CourseRepository;
import danceschool.javaversion.repository.DanceClassRepository;
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
@CacheConfig(cacheNames = { "_classes_" }) // tells Spring where to store
public class DanceClassService {

  @Autowired
  DanceClassRepository repository;

  @Autowired
  CourseRepository courseRepository;

  @Cacheable
  public List<DanceClassDTO> getAll(int page, int pageSize) {
    Pageable paging = PageRequest.of(
      page,
      pageSize,
      Sort.by("startDate").descending()
    );

    List<DanceClass> classes = repository.findAll(paging).getContent();

    return listToDTO(classes);
  }

  private DanceClassDTO convertToDanceClassDTO(DanceClass danceClass) {
    return new DanceClassDTO(danceClass);
  }

  private List<DanceClassDTO> iterableToDTO(Iterable<DanceClass> classes) {
    return StreamSupport
      .stream(classes.spliterator(), false)
      .map(this::convertToDanceClassDTO)
      .collect(Collectors.toList());
  }

  private List<DanceClassDTO> listToDTO(List<DanceClass> classes) {
    return classes
      .stream()
      .map(this::convertToDanceClassDTO)
      .collect(Collectors.toList());
  }

  public List<DanceClassDTO> getByIds(List<Integer> ids) {
    List<DanceClass> danceClasses = repository.getByIds(ids);
    return listToDTO(danceClasses);
  }

  @Cacheable
  public List<DanceClassDTO> getTop() {
    return repository
      .getTop()
      .stream()
      .map(this::convertToDanceClassDTO)
      .collect(Collectors.toList());
  }

  @Cacheable
  public List<DanceClassDTO> findByCourse(Long id) {
    List<DanceClass> danceClasses = repository.findByCourse(id);
    return listToDTO(danceClasses);
  }

  @CachePut
  public Long create(DanceClass entity) throws Exception {
    try {
      entity = repository.save(entity);
      Optional<Course> course = courseRepository.findById(entity.getCourseID());
      if (course.isPresent()) {
        course.get().getDanceClasses().add(entity);
        return entity.getId();
      }
    } catch (Exception e) {
      throw e;
    }
    return null;
  }

  @CachePut
  public boolean update(DanceClass entity) throws RecordNotFoundException {
    Optional<DanceClass> danceClass = repository.findById(entity.getId());

    if (danceClass.isPresent()) {
      DanceClass newEntity = danceClass.get();
      newEntity.setStartTime(entity.getStartTime());
      newEntity.setEndTime(entity.getEndTime());
      newEntity.setCourseName(entity.getCourseName());
      newEntity = repository.save(newEntity);
      return true;
    } else {
      return false;
    }
  }

  @CacheEvict(allEntries = true)
  public void delete(Long id) throws RecordNotFoundException {
    Optional<DanceClass> danceClass = repository.findById(id);

    if (danceClass.isPresent()) {
      // DanceClass entity = danceClass.get();
      repository.deleteById(id);
      // Course course = courseRepository.findById(entity.getCourseID);
      //  course.getDanceClassess.remove(entity);
    } else {
      /**  throw new RecordNotFoundException(
        "No DanceClass record exist for given id"
      ); */
    }
  }
}
