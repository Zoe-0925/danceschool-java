package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository
  extends PagingAndSortingRepository<Booking, Long> {
  @Query("select u from Booking u where u.courseID =:id")
  List<Booking> getByCourse(Long id);

  @Query("select u from Booking u where u.studentID =:id")
  List<Booking> getByStudent(Long id);


}
