package entities;

import java.sql.SQLException;
import utilities.ConexionMySQL;

public class User {
    public String username;
    public String password;
    public int id;

    public User (String username, String password) {
        this.username = username;
        this.password = password;
        this.id = 0;
    }

    public User (String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
