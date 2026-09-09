package example.exception;
import java.util.List;

public class ErrorResponse {
    private int status;
    private List<String> messages;

    public ErrorResponse(int status, List<String> messages) {
        this.status = status;
        this.messages = messages;
    }

    public int getStatus() {
        return status;
    }

    public List<String> getMessage() {
        return messages;
    }
}
