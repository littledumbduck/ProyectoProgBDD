package DAO;

import entities.Customer;
import utilities.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    private Customer customer;

    public CustomerDAO(Customer customer) {
        this.customer = customer;
    }

    public void addCustomer() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "INSERT INTO `book`(`dni`, `name`, `surname`, `email`, `phonenumber`) VALUES (?,?,?,?,?)";

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setString(1, this.customer.getDni());
            pstmt.setString(2, this.customer.getName());
            pstmt.setString(3, this.customer.getSurname());
            pstmt.setString(4, this.customer.getEmail());
            pstmt.setString(5, this.customer.getPhoneNumber());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteCustomer() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "DELETE FROM `customer` WHERE dni = ?";

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setString(1, this.customer.getDni());

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet searchCustomer() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "SELECT * FROM customer WHERE dni = ?";
        ResultSet rs = null;

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setString(1, this.customer.getDni());

            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

}




















