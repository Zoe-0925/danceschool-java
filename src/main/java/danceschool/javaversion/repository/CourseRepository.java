package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface CourseRepository
  extends PagingAndSortingRepository<Course, Long> {
  @Query("select u from Course u where u.name like :name")
  Page<Post> findByName(@Param("name") String name, Pageable pageReq);

  default Page<Post> findByName(Course course, Pageable pageReq) {
    return findByName(course.getName(), pageReq);
  }
}
