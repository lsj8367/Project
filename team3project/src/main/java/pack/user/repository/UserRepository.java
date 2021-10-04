package pack.user.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pack.user.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByUserDcountGreaterThan(long userDcount);

}
