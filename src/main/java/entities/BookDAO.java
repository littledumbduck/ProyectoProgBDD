package entities;

import utilities.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
    private Book book;
    private ConexionMySQL sql;

    public BookDAO (Book book) {
        this.book = book;
        this.sql = new ConexionMySQL();
    }

    public void addBook() throws SQLException {
        ConexionMySQL db = new ConexionMySQL();
        String sql = "INSERT INTO `book`(`idBook`, `customer_dni`, `room_id`, `dateEntry`, `dateLeave`, `purchaseStatus`) " +
                "VALUES (?,?,?,?,?,?)";

        try (PreparedStatement pstmt = db.executeStatement(sql)) {
            pstmt.setInt(1, this.book.getIdBook());
            pstmt.setString(2, this.book.getCustomerDni());
            pstmt.setInt(3, this.book.getRoomId());
            pstmt.setString(4, this.book.getDateEntry());
            pstmt.setString(5, this.book.getDateLeave());
            pstmt.setString(6, String.valueOf(this.book.getPurchaseStatus()));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
