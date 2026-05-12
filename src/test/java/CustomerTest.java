import DAO.CustomerDAO;
import entities.Customer;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerTest {
    @Test
    public void addCustomer() throws SQLException {
        Customer customer = new Customer("30238455B", "Alberto", "Larioss", "Email", "123456789");
        CustomerDAO customerDAO = new CustomerDAO(customer);

        customerDAO.addCustomer();
    }

    @Test
    void showCustomer() throws SQLException {
        Customer customer = new Customer();
        CustomerDAO customerDAO = new CustomerDAO(customer);

        ResultSet rs = customerDAO.searchCustomer("30238456B");

        while (rs.next()) {
            System.out.println("Dni: " + rs.getString("dni"));
            System.out.println("Nombre: " + rs.getString("name"));
            System.out.println("Apellido: " + rs.getString("surname"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Teléfono: " + rs.getString("phonenumber"));
        }
    }
}
