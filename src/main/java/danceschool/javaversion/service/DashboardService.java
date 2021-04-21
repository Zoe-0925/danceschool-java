package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Dashboard;
import danceschool.javaversion.repository.DashboardRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Dashboardervice {

  @Autowired
  DashboardRepository repository;

  public List<Dashboard> getDashboard() {
    //TODO
  }
}
