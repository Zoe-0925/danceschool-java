package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DanceClassRepository
  extends PagingAndSortingRepository<DanceClass, Long> {
  @Query("select u from DanceClass u where u.CourseID = id")
  List<DanceClass> findByCourse(Long id);
}
