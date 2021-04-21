package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
public interface DashboardRepository extends PagingAndSortingRepository<DanceClass, Long> {
  List<DanceClass> getDashboard();
}
