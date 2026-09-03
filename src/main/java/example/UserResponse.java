package example;

public class UserResponse {

    private String name;
    private int age;
    private String message;

    public UserResponse(String name, int age, String message) {
        this.name = name;
        this.age = age;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMessage() {
        return message;
    }
}