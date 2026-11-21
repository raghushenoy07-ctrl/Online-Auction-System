public class User {

    // Stores the user's full name
    private String name;

    // Stores the user's login username
    private String username;

    // Stores the user's password
    private String password;

    // Constructor initializes user information
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // Returns the user's full name
    public String getName() {
        return name;
    }

    // Returns the user's username
    public String getUsername() {
        return username;
    }
}
