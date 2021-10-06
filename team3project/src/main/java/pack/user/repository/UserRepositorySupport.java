package pack.user.repository;

import java.util.List;
import pack.user.domain.User;
import pack.user.model.UserDto;

public interface UserRepositorySupport {
    List<UserDto> selectRefuseCount();
    User selectBestRead();
}
