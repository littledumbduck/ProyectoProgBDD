package entities;

import utilities.ConexionMySQL;

import java.sql.SQLException;

public class UserDAO {
    private User user;
    private ConexionMySQL sql;

    public UserDAO (User user) {
        this.user = user;
        this.sql = new ConexionMySQL();
    }

    public void createUsername () throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();

        sql.ejecutarInsertDeleteUpdate("INSERT INTO user (username, password)\n VALUES ('" + this.user.getUsername() + "', '" + this.user.getPassword() + "');");
    }

    public void deleteUsername () throws SQLException {
        sql.ejecutarInsertDeleteUpdate("DELETE FROM `user` WHERE username = " + this.user.getUsername());
    }
}