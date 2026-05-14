package frames;

import DAO.CustomerDAO;
import DAO.RoomDAO;
import entities.Customer;
import entities.Room;
import entities.Book;
import DAO.BookDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckInForm {
    private JList<Customer> customerList;
    private JList<Room> roomList;
    private JButton realizarCheckInButton;
    private JPanel checkInPanel;
    private JTextField fechaEntradaTxt;
    private JTextField fechaSalidaTxt;
    private JList<Book> bookList;
    private JButton realizarCheckOutButton;

    public CheckInForm() {
        loadLists();

        realizarCheckInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarCheckIn();
            }
        });

        realizarCheckOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book reserva = bookList.getSelectedValue();
                if (reserva != null) {
                    try {
                        Room hab = new Room();
                        hab.setRoomNumber(reserva.getRoomId());
                        hab.setStatus('d');
                        RoomDAO rDao = new RoomDAO(hab);
                        rDao.updateRoom();

                        Book b = new Book();
                        b.setIdBook(b.getIdBook());
                        BookDAO bDao = new BookDAO(b);
                        bDao.deleteBook();

                        loadLists();
                        JOptionPane.showMessageDialog(null, "Check-out realizado.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void ejecutarCheckIn() {
        Customer cliente = customerList.getSelectedValue();
        Room habitacion = roomList.getSelectedValue();

        if (cliente == null || habitacion == null) {
            JOptionPane.showMessageDialog(null, "Selecciona un cliente y una habitación.");
            return;
        }

        try {
            Book nuevaReserva = new Book();
            nuevaReserva.setCustomerDni(cliente.getDni());
            nuevaReserva.setRoomId(habitacion.getRoomNumber());
            nuevaReserva.setDateEntry(fechaEntradaTxt.getText());
            nuevaReserva.setDateLeave(fechaSalidaTxt.getText());
            nuevaReserva.setPurchaseStatus('p');

            BookDAO bDao = new BookDAO(nuevaReserva);
            bDao.addBook();

            habitacion.setStatus('o');
            RoomDAO rDao = new RoomDAO(habitacion);
            rDao.updateRoom();

            JOptionPane.showMessageDialog(null, "Check-in realizado correctamente.");
            loadLists();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void loadLists() {
        try {
            DefaultListModel<Customer> modeloClientes = new DefaultListModel<>();
            CustomerDAO cDAO = new CustomerDAO(new Customer());
            ResultSet rsC = cDAO.getAllCustomers();
            while (rsC.next()) {
                modeloClientes.addElement(new Customer(
                        rsC.getString("dni"), rsC.getString("name"),
                        rsC.getString("surname"), rsC.getString("email"),
                        rsC.getString("phonenumber")));
            }
            customerList.setModel(modeloClientes);

            DefaultListModel<Room> modeloHabitaciones = new DefaultListModel<>();
            RoomDAO rDAO = new RoomDAO(new Room());
            ResultSet rsR = rDAO.getAllRooms();
            while (rsR.next()) {
                char status = rsR.getString("status").toLowerCase().charAt(0);
                if (status == 'd') {
                    modeloHabitaciones.addElement(new Room(
                            rsR.getInt("roomNumber"), rsR.getInt("roomfloor"),
                            rsR.getString("roomType"), rsR.getDouble("price"),
                            status));
                }
            }
            roomList.setModel(modeloHabitaciones);

            DefaultListModel<Book> modeloReservas = new DefaultListModel<>();
            BookDAO bDAO = new BookDAO();
            ResultSet rsB = bDAO.getAllBooks();
            while (rsB.next()) {
                Book b = new Book();
                b.setIdBook(rsB.getInt("idBook"));
                b.setCustomerDni(rsB.getString("customer_dni"));
                b.setRoomId(rsB.getInt("room_id"));
                b.setDateEntry(rsB.getString("dateEntry"));
                b.setDateLeave(rsB.getString("dateLeave"));
                b.setPurchaseStatus(rsB.getString("purchaseStatus").charAt(0));
                modeloReservas.addElement(b);
            }
            bookList.setModel(modeloReservas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JPanel getCheckInPanel() {
        return checkInPanel;
    }
}