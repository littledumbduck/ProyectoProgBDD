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

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateBook() throws SQLException {
        ConexionMySQL db = new ConexionMySQL();
        String sql = "UPDATE `book` SET `idBook`='?',`customer_dni`='?',`room_id`='?',`dateEntry`='?'," +
                "`dateLeave`='?',`purchaseStatus`='?' WHERE idBook = ?";

        try (PreparedStatement pstmt = db.executeStatement(sql)) {
            pstmt.setInt(1, this.book.getIdBook());
            pstmt.setString(2, this.book.getCustomerDni());
            pstmt.setInt(3, this.book.getRoomId());
            pstmt.setString(4, this.book.getDateEntry());
            pstmt.setString(5, this.book.getDateLeave());
            pstmt.setString(6, String.valueOf(this.book.getPurchaseStatus()));
            pstmt.setInt(7, this.book.getIdBook());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteBook() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "DELETE FROM `book` WHERE idBook = ?";

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setInt(1, this.book.getIdBook());

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet searchBook() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "SELECT * FROM book WHERE idBook = ?";
        ResultSet rs = null;

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setInt(1, this.book.getIdBook());

            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet searchBookByCustomer(String dni) throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "SELECT * FROM book WHERE dni_customer = ?";
        ResultSet rs = null;

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setString(1, dni);

            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet searchBookByRoom(String roomNumber) throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "SELECT * FROM book WHERE room_id = ?";
        ResultSet rs = null;

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setString(1, roomNumber);

            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }
}
