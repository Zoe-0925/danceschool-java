package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository
  extends PagingAndSortingRepository<DanceClass, Long> {
  List<Dashboard> getDashboard();
}
