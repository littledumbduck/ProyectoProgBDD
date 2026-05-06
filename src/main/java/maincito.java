import utilities.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class maincito {

    public static void main(String[] args) {
        ConexionMySQL db = new ConexionMySQL();
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (PreparedStatement pstmt = db.executeStatement(sql)) {
            String name = "admin";
            String password = "admin";
            pstmt.setString(1, name);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("¡Login exitoso! Bienvenido " + rs.getString("username"));
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
