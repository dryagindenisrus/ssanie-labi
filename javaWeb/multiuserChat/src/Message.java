import java.io.Serializable;

public class Message implements Serializable {

    public String username;
    public String messageText;
    public int usernameId;

    public Message(String username, String messageText, int usernameId) {
        this.username = username;
        this.messageText = messageText;
        this.usernameId = usernameId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", messageText='" + messageText + '\'' +
                ", usernameId=" + usernameId +
                '}';
    }
}
