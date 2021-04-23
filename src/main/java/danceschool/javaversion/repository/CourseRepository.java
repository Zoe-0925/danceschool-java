package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface CourseRepository
  extends PagingAndSortingRepository<Course, Long> {
  @Query("select u from Course u where u.name like :name")
  Page<Course> findByName(@Param("name") String name, Pageable pageReq);

  default Page<Course> findByName(Course course, Pageable pageReq) {
    return findByName(course.getName(), pageReq);
  }
}
