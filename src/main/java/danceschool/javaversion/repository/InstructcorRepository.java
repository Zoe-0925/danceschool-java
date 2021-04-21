package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InstructcorRepository
  extends PagingAndSortingRepository<Instructcor, Long> {}
