package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface CourseRepository
  extends PagingAndSortingRepository<Course, Long> {}
