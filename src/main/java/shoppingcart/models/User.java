package shoppingcart.models;

public class User {
    private final String id;
    private final String name;
    private final String mail;

    public User(String id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }
}
