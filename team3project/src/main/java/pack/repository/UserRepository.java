package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
