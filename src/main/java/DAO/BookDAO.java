package DAO;

import entities.Book;
import utilities.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
    private Book book;

    public BookDAO (Book book) {
        this.book = book;
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

        }
    }

    public void updateBook() throws SQLException {
        ConexionMySQL db = new ConexionMySQL();
        String sql = "UPDATE `book` SET `idBook`=?,`customer_dni`=?,`room_id`=?,`dateEntry`=?," +
                "`dateLeave`=?,`purchaseStatus`=? WHERE idBook = ?";

        try (PreparedStatement pstmt = db.executeStatement(sql)) {
            pstmt.setInt(1, this.book.getIdBook());
            pstmt.setString(2, this.book.getCustomerDni());
            pstmt.setInt(3, this.book.getRoomId());
            pstmt.setString(4, this.book.getDateEntry());
            pstmt.setString(5, this.book.getDateLeave());
            pstmt.setString(6, String.valueOf(this.book.getPurchaseStatus()));
            pstmt.setInt(7, this.book.getIdBook());

            pstmt.executeUpdate();

        }
    }

    public void deleteBook() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "DELETE FROM `book` WHERE idBook = ?";

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setInt(1, this.book.getIdBook());
            pstmt.execute();
        }
    }

    public ResultSet searchBook(int bookId) throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "SELECT * FROM book WHERE idBook = ?";
        PreparedStatement pstmt = sql.executeStatement(statement);
        pstmt.setInt(1, bookId);
        return pstmt.executeQuery();
    }

    public ResultSet searchBookByCustomer(String dni) throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "SELECT * FROM book WHERE customer_dni = ?";
        PreparedStatement pstmt = sql.executeStatement(statement);
        pstmt.setString(1, dni);
        return pstmt.executeQuery();
    }

    public ResultSet searchBookByRoom(int roomId) throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "SELECT * FROM book WHERE room_id = ?";
        PreparedStatement pstmt = sql.executeStatement(statement);
        pstmt.setInt(1, roomId);
        return pstmt.executeQuery();
    }
}
