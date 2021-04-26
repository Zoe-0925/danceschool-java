package danceschool.javaversion.repository;

import danceschool.javaversion.model.Membership;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository
  extends PagingAndSortingRepository<Membership, Long> {
  @Query("select u from Membership u where u.name=:name")
  List<Membership> findByName(@Param("name") String name);
}
