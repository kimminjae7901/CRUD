package example.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

public class PostRequest {

    @NotBlank(message="제목은 필수입니다.")
    private String title;

    @NotBlank(message="내용은 필수입니다.")
    private String content;

    @Min(value=0, message="잘못된 아이디")
    private Long userId;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}