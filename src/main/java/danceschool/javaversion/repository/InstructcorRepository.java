package danceschool.javaversion.repository;

import danceschool.javaversion.model.Instructcor;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface InstructcorRepository
  extends PagingAndSortingRepository<Instructcor, Long> {
  @Query(
    "select u from Instructor u where u.firstName LIKE :name or u.lastName LIKE :name"
  )
  Page<Instructcor> findByName(@Param("name") String name, Pageable pageReq);

  default Page<Instructcor> findByName(
    Instructcor Instructor,
    Pageable pageReq
  ) {
    return findByUser(Instructor.getName(), pageReq);
  }
}
