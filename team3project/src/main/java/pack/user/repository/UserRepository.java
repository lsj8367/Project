package pack.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.user.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
