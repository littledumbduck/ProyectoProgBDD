package DAO;

import entities.User;
import utilities.ConexionMySQL;

import java.sql.PreparedStatement;
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

        sql.executeStatement("INSERT INTO user (username, password)\n VALUES ('" + this.user.getUsername() + "', '" + this.user.getPassword().hashCode() + "');");
    }

    public void deleteUsername () throws SQLException {
        sql.executeStatement("DELETE FROM `user` WHERE username = " + this.user.getUsername());
    }

    public boolean checkLogin () throws SQLException {
        ConexionMySQL db = new ConexionMySQL();
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (PreparedStatement pstmt = db.executeStatement(sql)) {
            pstmt.setString(1, this.user.getUsername());
            pstmt.setString(2, this.user.getPassword());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}