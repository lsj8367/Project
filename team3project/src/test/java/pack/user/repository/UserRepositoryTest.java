package pack.user.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import pack.config.QuerydslConfig;

@DataJpaTest
@Import(QuerydslConfig.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("selectUserAll, selectUserPointAll 같이씀")
    void selectUserAll() {
        userRepository.findAll();
    }

    @Test
    void selectDelay() {
        userRepository.findAllByUserDcountGreaterThan(5);
    }



}