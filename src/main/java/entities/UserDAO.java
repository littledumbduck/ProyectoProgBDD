package entities;

import utilities.ConexionMySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private User user;
    private ConexionMySQL sql;
    private ResultSet resulSet;

    public UserDAO (User user) {
        this.user = user;
        this.sql = new ConexionMySQL();
        this.resulSet = null;
    }

    public void createUsername () throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();

        sql.ejecutarInsertDeleteUpdate("INSERT INTO user (username, password)\n VALUES ('" + this.user.getUsername() + "', '" + this.user.getPassword().hashCode() + "');");
    }

    public void deleteUsername () throws SQLException {
        sql.ejecutarInsertDeleteUpdate("DELETE FROM `user` WHERE username = " + this.user.getUsername());
    }

    public boolean checkLogin () throws SQLException {
        resulSet = sql.ejecutarSelect("SELECT * FROM user WHERE username = '" + this.user.getUsername() + "' AND password = ¡" + this.user.getPassword() + "';");

        while (resulSet.next()) {
            return true;
        }
        return false;
    }
}