package pack.user.service;

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

    public User selectBestRead() {
        return userRepository.selectBestRead();
    }

    public User selectUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new RuntimeException("해당하는 유저 없음");
        });
    }

    public boolean validationCheck(String id) {
        return userRepository.existsByUserId(id);
    }

    @Transactional
    public void signUp(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void usePoint(UserDto userDto) {
        User user = userRepository.findById(userDto.getUser_id()).orElseThrow(() -> {
            throw new RuntimeException("해당하는 유저 없음");
        });

        user.setUserPoint(user.getUserPoint() - userDto.getUser_point());
    }

    @Transactional
    public void minusRentPoint(String userId, int delPoint) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new RuntimeException("해당하는 유저 없음");
        });

        user.setUserPoint(user.getUserPoint() - delPoint);
    }

}
