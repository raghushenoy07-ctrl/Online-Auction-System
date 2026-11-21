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

    // Checks whether the entered login credentials are correct
    public boolean checkLogin(String username, String password) {

        // Compares entered username and password with stored credentials
        if (this.username.equals(username) && this.password.equals(password)) {
            return true; // Returns true if both match
        }

        return false; // Returns false if credentials do not match
    }
}