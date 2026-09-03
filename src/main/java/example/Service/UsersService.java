package example.Service;

import org.springframework.stereotype.Service;

import example.DTO.UserRequest;
import example.DTO.UserResponse;
import example.Entity.User;
import example.Repository.UserRepository;

@Service
public class UsersService {

    private final UserRepository userRepository;
    public UsersService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public UserResponse getUser(long id)
    {
        User readUser= userRepository.findById(id).orElseThrow();
        return new UserResponse(
            readUser.getName(),
            readUser.getAge(),
            "사용자 조회에 성공했습니다."
        );
    }

    public UserResponse createUser(UserRequest userRequest) {

        User user = new User();

        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());

        User savedUser = userRepository.save(user);

        return new UserResponse(
            savedUser.getName(),
            savedUser.getAge(),
            "회원가입이 완료되었습니다."
        );
    }
}
