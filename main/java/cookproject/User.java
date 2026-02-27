package cookproject;

public class User {
    private String email;
    private String password;
    private UserRole role;
    private String username;

    // Constructor كامل يشمل كل الحقول
    public User(String username, String email, String password, UserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Constructor مع email، password، role
    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Constructor مع email و password (role افتراضي CUSTOMER)
    public User(String email, String password) {
        this(email, password, UserRole.CUSTOMER);
    }

    // Constructor مختصر: username و email
    public User(String username, String email, boolean isUsernameEmail) {
        this.username = username;
        this.email = email;
        this.role = UserRole.CUSTOMER;
    }

    // ======================= Getters & Setters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName() {
        if (username != null && !username.isEmpty()) {
            return username;
        }
        if (email != null && email.contains("@")) {
            return email.substring(0, email.indexOf("@"));
        }
        return "Unknown";
    }

    public String getId() {
        return String.valueOf(email != null ? email.hashCode() : 0);
    }
}
