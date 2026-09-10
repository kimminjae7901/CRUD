package example.DTO;

public class PostResponse {

    private String title;
    private String content;
    private Long userId;

    public PostResponse(String title, String content, Long userId)
    {
        this.title=title;
        this.content=content;
        this.userId=userId;
    }
    
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
