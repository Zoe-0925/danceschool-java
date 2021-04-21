package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Course;
import danceschool.javaversion.repository.CourseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  @Autowired
  CourseRepository repository;

  public List<Course> getAll() {
    List<Course> courses = new ArrayList<Course>();

    // sort=[field, direction]
    courses.add(new Course(getSortDirection(sort[1]), sort[0]));

    Pageable pagingSort = PageRequest.of(page, size, Sort.by(courses));

    Page<Course> pages = repository.findAll(pageReq);

    //TODO DTO

    return pages.getContent();
  }

  //TODO
  public List<Course> findByName(String name) {}

  public CourseWithCountDTO findWithCount() {
    List<Course> CourseList = repository.findAll();

    if (CourseList.size() > 0) {
      return new CourseWithCountDTO(CourseList, CourseList.length());
    } else {
      return new CourseWithCountDTO(new ArrayList<Course>(), 0);
    }
  }

  public int create(Course entity) {
    Course created = repository.save(entity);
    return created.getId();
  }

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

  public void delete(int id) throws RecordNotFoundException {
    Optional<Course> Course = repository.findById(id);

    if (Course.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new RecordNotFoundException("No Course record exist for given id");
    }
  }
}
