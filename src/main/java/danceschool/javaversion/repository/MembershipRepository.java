package danceschool.javaversion.repository;

import danceschool.javaversion.model.Membership;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository
  extends PagingAndSortingRepository<Membership, Long> {
  @Query("select u from Membership u where u.name=:name")
  Page<Membership> findByName(@Param("name") String name, Pageable pageReq);

  default Page<Membership> findByName(Membership Membership, Pageable pageReq) {
    return findByUser(Membership.getName(), pageReq);
  }
}
