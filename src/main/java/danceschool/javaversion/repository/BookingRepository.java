package danceschool.javaversion.repository;

import danceschool.javaversion.dto.CountByDate;
import danceschool.javaversion.dto.MembershipNameWithCountDTO;
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

  @Query("select Count(*) from Booking")
  int getCount();

  @Query(
    "SELECT Top 3 id, COUNT(*) as count FROM Booking  GROUP BY classId order by count desc"
  )
  List<Integer> getTopDanceClassIDs();

  @Query(
    "SELECT d.startTime as date,Count(*) as count FROM Booking b inner join DanceClass d on b.classID = d.id where d.startTime >= DATEADD(day,-7, GETDATE()) GROUP BY d.id"
  )
  List<CountByDate> getLastWeeksBookings();

  @Query(
    "SELECT m.name as membershipName ,Count(*) as count FROM Booking b inner join Membership m on b.membershipID = d.id GROUP BY d.id"
  )
  List<MembershipNameWithCountDTO> getByMembership();
}
