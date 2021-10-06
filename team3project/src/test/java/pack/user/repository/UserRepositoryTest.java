package pack.user.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import pack.config.QuerydslConfig;
import pack.user.domain.User;

@DataJpaTest
@Import(QuerydslConfig.class)
class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

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

    @Test
    void selectRefuseCount() {
        userRepository.selectRefuseCount();
    }

    @Test
    void selectDelUser() {
        userRepository.findAllByUserPenalty("1+2+3");
    }

    @Test
    void selectUserPointCheck() {
        userRepository.findAllByUserPenaltyNotAndUserPenaltyNotAndUserPenaltyNot("1+2+3", "x", "4");
    }

    @Test
    void upDcount() {
        User user = userRepository.saveAndFlush(User.builder()
            .userId("test1")
            .userName("test")
            .userDcount(0)
            .build());

        user.setUserDcount(user.getUserDcount() + 1);
        userRepository.flush();
        testEntityManager.clear();

        final List<User> userList = userRepository.findAll();
        assertThat(userList.get(0).getUserDcount()).isEqualTo(1);
    }

    @Test
    @DisplayName("upPenalty, upDelUser 똑같이 update")
    void userPenalty() {
        User user = userRepository.saveAndFlush(User.builder()
            .userId("test1")
            .userName("test")
            .userPenalty("1+2")
            .build());

        user.setUserPenalty("1+2+3");
        userRepository.flush();
        testEntityManager.clear();

        final List<User> userList = userRepository.findAll();
        assertThat(userList.get(0).getUserPenalty()).isEqualTo("1+2+3");
    }

    @Test
    void updateUserPoint() {
        User user = userRepository.saveAndFlush(User.builder()
            .userId("test1")
            .userName("test")
            .userPoint(1000)
            .build());

        user.setUserPoint(user.getUserPoint() + 5000);
        userRepository.flush();
        testEntityManager.clear();

        final List<User> userList = userRepository.findAll();
        assertThat(userList.get(0).getUserPoint()).isEqualTo(6000);
    }

    @Test
    void selectBestRead() {
        userRepository.saveAndFlush(User.builder()
            .userId("test1")
            .userName("test1")
            .userPoint(1000)
            .userRentcnt(5)
            .build());
        userRepository.saveAndFlush(User.builder()
            .userId("test2")
            .userName("test2")
            .userPoint(1000)
            .userRentcnt(1)
            .build());

        testEntityManager.clear();

        User resultUser = userRepository.selectBestRead();
        assertThat(resultUser.getUserRentcnt()).isEqualTo(5);
    }

    @Test
    @DisplayName("selectUser, checkUserId, viewPoint 똑같은 로직")
    void selectUser() {
        userRepository.saveAndFlush(User.builder()
            .userId("test1")
            .userName("test1")
            .userPoint(1000)
            .userRentcnt(5)
            .build());

        testEntityManager.clear();

        User user = userRepository.findById("test1")
            .orElseThrow(() -> {
                throw new RuntimeException("해당하는 유저 없음");
            });

        assertThat(user.getUserId()).isEqualTo("test1");
    }

    @Test
    @DisplayName("usePoint, minusRentPoint, delPointUser 같은 로직")
    void usePoint() {
        //minusRentPoint의 경우에는 1000 고정
        userRepository.saveAndFlush(User.builder()
            .userId("test1")
            .userName("test1")
            .userPoint(1000)
            .build());

        testEntityManager.clear();

        User user = userRepository.findById("test1")
            .orElseThrow(() -> {
                throw new RuntimeException("해당하는 유저 없음");
            });
        user.setUserPoint(user.getUserPoint() - 500);
        userRepository.flush();

        assertThat(user.getUserPoint()).isEqualTo(500);

    }


}