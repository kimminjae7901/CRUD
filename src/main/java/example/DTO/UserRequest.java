package example.DTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public class UserRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Max(value=100, message= "나이는 100 이하이어야 합니다.")
    @Min(value = 0, message = "나이는 0 이상이어야 합니다.")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
