package danceschool.javaversion.service;

import danceschool.javaversion.dto.DanceClassDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.helper.SortDirection;
import danceschool.javaversion.model.Course;
import danceschool.javaversion.model.DanceClass;
import danceschool.javaversion.repository.CourseRepository;
import danceschool.javaversion.repository.DanceClassRepository;
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
import org.springframework.data.domain.Page;
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
  public List<DanceClassDTO> getAll(int page, int size, String[] sort) {
    List<DanceClass> classes = new ArrayList<DanceClass>();

    // sort=[field, direction]
    classes.add(
      new DanceClass(SortDirection.getSortDirection(sort[1]), sort[0])
    );

    Pageable pagingSort = PageRequest.of(page, size, Sort.by(courses));

    List<DanceClassDTO> result = repository
      .findAll(pageReq)
      .getContent()
      .stream()
      .map(this::convertToDanceClassDTO)
      .collect(Collectors.toList());

    return result;
  }

  private DanceClassDTO convertToDanceClassDTO(DanceClass danceClass) {
    return new DanceClassDTO(danceClass);
  }

  //TODO
  @Cacheable
  public List<DanceClass> findByCourse(Long id) {}

  @CachePut
  public Long create(DanceClass entity) throws Exception {
    try {
      entity = repository.save(entity);
      Course course = courseRepository.findById(entity.getCourseID());
      course.getDanceClassess().add(entity);

      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  @CachePut
  public DanceClass Update(DanceClass entity) throws RecordNotFoundException {
    Optional<DanceClass> danceClass = repository.findById(entity.getId());

    if (danceClass.isPresent()) {
      DanceClass newEntity = danceClass.get();
      newEntity.setStartTime(entity.getStartTime());
      newEntity.setEndTime(entity.getEndTime());
      newEntity.setCourseName(entity.getCourseName());
      newEntity = repository.save(newEntity);

      return newEntity;
    } else {
      return null;
    }
  }

  @CacheEvict(allEntries = true)
  public void delete(Long id) throws RecordNotFoundException {
    Optional<DanceClass> danceClass = repository.findById(id);

    if (danceClass.isPresent()) {
      DanceClass entity = danceClass.get();
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
