package example;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final UserRepository userRepository;
    public HelloService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public String getUser(int id)
    {
        return "사용자 ID "+id;
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
