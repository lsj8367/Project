package pack.user.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.user.domain.User;
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

}
