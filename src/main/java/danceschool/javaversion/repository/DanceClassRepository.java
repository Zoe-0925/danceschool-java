package danceschool.javaversion.repository;

import danceschool.javaversion.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DanceClassRepository
  extends PagingAndSortingRepository<DanceClass, Long> {
  @Query("select u from DanceClass u where u.CourseID = :id")
  List<DanceClass> findByCourse(Long id);

  //TODO check.
  @Query("select u from DanceClass u where u.id in :ids")
  List<DanceClass> getByIds(List<Integer> ids);

  @Query(
    "select *, Count(*) as count from DanceClass d right outer join Booking b on d.id= b.classId group by b.classId order by count DESC"
  )
  List<DanceClass> getTop();
}
