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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = { "_classes_" }) // tells Spring where to store
public class DanceClassService {

  @Autowired
  DanceClassRepository repository;

  @Autowired
  CourseRepository courseRepository;

  @Cacheable
  public List<DanceClass> getAll() {
    List<DanceClass> classes = new ArrayList<DanceClass>();

    // sort=[field, direction]
    classes.add(
      new DanceClass(SortDirection.getSortDirection(sort[1]), sort[0])
    );

    Pageable pagingSort = PageRequest.of(page, size, Sort.by(courses));

    Page<DanceClass> result = repository
      .findAll(pageReq)
      .getContent()
      .stream()
      .map(this::convertToDanceClassDTO)
      .collect(Collectors.toList());

    return result;
  }

  private DanceClassDTO convertToDanceClassDTO(DanceClass danceClass) {
    DanceClassDTO danceClassDTO = new DanceClassDTO();
    danceClassDTO.setId(danceClass.getId());
    danceClassDTO.setStartTime(danceClass.getStartTime());
    danceClassDTO.setEndTime(danceClass.getEndTime());
    danceClassDTO.setName(danceClass.getName());

    return danceClassDTO;
  }

  //TODO
  @Cacheable
  public List<DanceClass> findByCourse(int id) {}

  @CachePut
  public int create(DanceClass entity) throws Exception {
    try {
      entity = repository.save(entity);
      Course course = courseRepository.findById(entity.getCourseID);
      course.getDanceClassess.add(entity);

      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  @CachePut
  public DanceClass Update(DanceClass entity) throws RecordNotFoundException {
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

  @CacheEvict(allEntries = true)
  public void delete(int id) throws RecordNotFoundException {
    Optional<DanceClass> DanceClass = repository.findById(id);

    if (DanceClass.isPresent()) {
      repository.deleteById(id);
      Course course = courseRepository.findById(entity.getCourseID);
      course.getDanceClassess.remove(entity);
    } else {
      throw new RecordNotFoundException(
        "No DanceClass record exist for given id"
      );
    }
  }
}
