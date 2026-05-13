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
    private JList list1;
    private JList list2;
    private JButton realizarCheckInButton;

    public CheckInForm() {
        realizarCheckInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Obtener los objetos seleccionados de las listas
                // Importante: Casteamos (convertimos) el objeto genérico al tipo de tu clase
                Customer clienteSeleccionado = (Customer) list1.getSelectedValue();
                Room habitacionSeleccionada = (Room) list2.getSelectedValue();

                // 2. Validación: Comprobar que el usuario ha seleccionado algo en ambas listas
                if (clienteSeleccionado == null || habitacionSeleccionada == null) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, selecciona un cliente y una habitación para continuar.",
                            "Selección incompleta",
                            JOptionPane.WARNING_MESSAGE);
                    return; // Salimos del método para que no intente crear la reserva
                }

                try {
                    // 3. Crear el objeto Book con los datos obtenidos
                    // Nota: He usado valores por defecto para fechas, deberías ajustarlo
                    // según lo que necesite tu base de datos (p.ej. "2024-05-20")
                    Book nuevaReserva = new Book();
                    nuevaReserva.setCustomerDni(clienteSeleccionado.getDni());
                    nuevaReserva.setRoomId(habitacionSeleccionada.getRoomNumber());
                    nuevaReserva.setDateEntry("2024-05-22"); // Aquí podrías usar un LocalDate.now()
                    nuevaReserva.setDateLeave("2024-05-25");
                    nuevaReserva.setPurchaseStatus('p'); // 'p' de pendiente, por ejemplo

                    // 4. Usar el DAO para guardar en la base de datos
                    BookDAO bookDAO = new BookDAO(nuevaReserva);
                    bookDAO.addBook();

                    // 5. Feedback al usuario y limpiar selección si quieres
                    JOptionPane.showMessageDialog(null, "¡Reserva realizada con éxito!");
                    list1.clearSelection();
                    list2.clearSelection();

                } catch (Exception ex) {
                    // Manejo de errores de SQL o conexión
                    JOptionPane.showMessageDialog(null,
                            "Error al guardar la reserva: " + ex.getMessage(),
                            "Error de base de datos",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    private void loadLists() {
        try {
            // Cargar Clientes
            DefaultListModel<Customer> modeloClientes = new DefaultListModel<>();
            CustomerDAO cDAO = new CustomerDAO(new Customer());
            ResultSet rsC = cDAO.getAllCustomers();

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
            ResultSet rsR = rDAO.getAllRooms();

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
