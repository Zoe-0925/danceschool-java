package danceschool.javaversion.repository;

import danceschool.javaversion.model.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository
  extends PagingAndSortingRepository<Student, Long> {
  @Query("select u from Student u where u.userName like :query")
  List<Student> findByName(@Param("query") String query);

  Optional<Student> findById(@Param("id") Long id);

  Optional<Student> findByEmail(@Param("email") String email);

  @Query("select Count(*) from Student")
  int getCount();
}
