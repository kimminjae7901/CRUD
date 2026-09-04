package example.Service;

import java.util.ArrayList;
import java.util.List;

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

    public UserResponse updateUser(long id,UserRequest userRequest){
        User updateuser=userRepository.findById(id).orElseThrow();
        updateuser.setName(userRequest.getName());
        updateuser.setAge(userRequest.getAge());

        userRepository.save(updateuser);

        return new UserResponse(
            updateuser.getName(),
            updateuser.getAge(),
            "사용자 정보 변경이 완료되었습니다."
        );
    }

    public String deleteUser(long id)
    {
        userRepository.deleteById(id);
        return "사용자 삭제가 완료되었습니다.";

    }

    public ArrayList<UserResponse> getAllUser()
    {
        List<User> users=userRepository.findAll();
        
        ArrayList<UserResponse> response = new ArrayList<>();
        for(int i=0; i<users.size(); i++)
        {
            User user=users.get(i);
            response.add(
                new UserResponse(
                    user.getName(),
                    user.getAge(),
                    "사용자 조회에 성공했습니다."
                )
            );
        }

        return response;
    }
}
