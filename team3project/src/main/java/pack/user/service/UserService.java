package pack.user.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.user.domain.User;
import pack.user.model.UserDto;
import pack.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> selectUserAll() {
        return userRepository.findAll();
    }

    public List<User> selectDelay() {
        return userRepository.findAllByUserDcountGreaterThan(5);
    }

    public List<UserDto> selectRefuseCount() {
        return userRepository.selectRefuseCount();
    }

    public List<User> selectDelUser() {
        return userRepository.findAllByUserPenalty("1+2+3");
    }

    public List<User> selectUserPcheck() {
        return userRepository.findAllByUserPenaltyNotAndUserPenaltyNotAndUserPenaltyNot("1+2+3", "x", "4");
    }

    @Transactional
    public void updatePenaltyAndPoint(String userId, int userPoint) {
        final Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.ifPresent(user -> {
            user.setUserPenalty("1");
            user.setUserPoint(user.getUserPoint() - userPoint);
        });
    }

}
