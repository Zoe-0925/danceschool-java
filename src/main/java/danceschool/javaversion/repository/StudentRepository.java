package danceschool.javaversion.repository;

import danceschool.javaversion.model.Student;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository
  extends PagingAndSortingRepository<Student, Long> {
  @Query("select u from Student u where u.userName like :name")
  Page<Student> findByName(@Param("name") String name, Pageable pageReq);

  default Page<Student> findByName(Student student, Pageable pageReq) {
    return findByUser(student.getUserName(), pageReq);
  }
}
