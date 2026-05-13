package frames;

import DAO.CustomerDAO;
import DAO.RoomDAO;
import entities.Customer;
import entities.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckInForm {
    private JList list1;
    private JList list2;
    private JButton realizarCheckInButton;

    public CheckInForm() {
        realizarCheckInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void loadLists() {
        try {
            // Cargar Clientes
            DefaultListModel<Customer> modeloClientes = new DefaultListModel<>();
            CustomerDAO cDAO = new CustomerDAO(new Customer());
            ResultSet rsC = cDAO.getAllCustomers(""); // El parámetro dni parece sobrar en tu DAO, pero lo pide

            while (rsC.next()) {
                modeloClientes.addElement(new Customer(
                        rsC.getString("dni"), rsC.getString("name"),
                        rsC.getString("surname"), rsC.getString("email"),
                        rsC.getString("phonenumber")));
            }
            list1.setModel(modeloClientes);

            // Cargar Habitaciones
            DefaultListModel<Room> modeloHabitaciones = new DefaultListModel<>();
            RoomDAO rDAO = new RoomDAO(new Room());
            ResultSet rsR = rDAO.getAllRooms("");

            while (rsR.next()) {
                if (rsR.getString("status").charAt(0) == 'd') {
                    modeloHabitaciones.addElement(new Room(
                            rsR.getInt("roomNumber"), rsR.getInt("roomfloor"),
                            rsR.getString("roomType"), rsR.getDouble("price"),
                            rsR.getString("status").charAt(0)));
                }
            }
            list2.setModel(modeloHabitaciones);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
