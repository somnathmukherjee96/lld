package splitwise.models;

public class User {
    private final String userId;
    private final String name;
    private final String emailId;

    public User(String userId, String name, String emailId) {
        this.userId = userId;
        this.name = name;
        this.emailId = emailId;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }
}
