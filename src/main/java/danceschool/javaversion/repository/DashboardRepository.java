package danceschool.javaversion.repository;

import danceschool.javaversion.dto.Dashboard;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository
  extends PagingAndSortingRepository<Dashboard, Long> {
  List<Dashboard> getDashboard();
}
