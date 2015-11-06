package entities;

/**
 * Created by radud on 27/10/2015.
 */
public class User {
    private String password;
    private String username;
    private String role;
    private int id;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {

        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
