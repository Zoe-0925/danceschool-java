package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository
  extends PagingAndSortingRepository<Course, Long> {
  @Query("select u from Course u where u.name like :name")
  List<Course> findByName(@Param("name") String name);

  Optional<Course> findById(Long id);
}
