import DAO.CustomerDAO;
import entities.Customer;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class CustomerTest {
    @Test
    public void addCustomer() throws SQLException {
        Customer customer = new Customer("30238455B", "Alberto", "Larioss", "Email", "123456789");
        CustomerDAO customerDAO = new CustomerDAO(customer);

        customerDAO.addCustomer();
    }
}
