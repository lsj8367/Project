package pack.user.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pack.user.domain.User;

public interface UserRepository extends JpaRepository<User, String>, UserRepositorySupport {

    List<User> findAllByUserDcountGreaterThan(long userDcount);
    List<User> findAllByUserPenalty(String userPenalty);
    List<User> findAllByUserPenaltyNotAndUserPenaltyNotAndUserPenaltyNot(String userPenalty, String userPenalty2, String userPenalty3);
    boolean existsByUserId(String userId);

}
