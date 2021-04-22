package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Dashboard;
import danceschool.javaversion.repository.DashboardRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@Service
@CacheConfig
public class DashboardService {

  @Autowired
  DashboardRepository repository;

  @Cacheable
  public List<Dashboard> getDashboard() {

    
    //TODO
  }
}
