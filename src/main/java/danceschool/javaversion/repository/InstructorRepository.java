package danceschool.javaversion.repository;

import danceschool.javaversion.dto.InstructorIDWithCountDTO;
import danceschool.javaversion.model.Instructor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository
  extends PagingAndSortingRepository<Instructor, Long> {
  @Query(
    "select u from Instructor u where u.firstName LIKE :name or u.lastName LIKE :name"
  )
  List<Instructor> findByName(@Param("name") String name);

  default List<Instructor> findByName(Instructor Instructor) {
    return findByName(Instructor.getFirstName());
  }

  Optional<Instructor> findById(@Param("id") Long id);

  List<Instructor> findAll();

  @Query("select i.id, top 3 Count(*) as count from Instructor i right outer join Booking b on i.id= b.classId group by b.classId order by count DESC")
  List<InstructorIDWithCountDTO> getTop();
}
