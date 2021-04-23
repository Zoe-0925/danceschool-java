package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository
  extends PagingAndSortingRepository<Course, Long> {
  @Query("select u from Course u where u.name like :name")
  Page<Course> findByName(@Param("name") String name, Pageable pageReq);

  default Page<Course> findByName(Course course, Pageable pageReq) {
    return findByName(course.getName(), pageReq);
  }

  Optional<Course> findById(Long id);
}
