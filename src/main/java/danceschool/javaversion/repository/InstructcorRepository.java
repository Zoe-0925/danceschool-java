package danceschool.javaversion.repository;

import danceschool.javaversion.model.Instructcor;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface InstructcorRepository
  extends PagingAndSortingRepository<Instructcor, Long> {
  @Query(
    "select u from Instructor u where u.firstName LIKE :name or u.lastName LIKE :name"
  )
  Page<Post> findByName(@Param("name") String name, Pageable pageReq);

  default Page<Post> findByName(Instructor Instructor, Pageable pageReq) {
    return findByUser(Instructor.getName(), pageReq);
  }
}
