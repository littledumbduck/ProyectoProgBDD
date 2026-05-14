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
    private JPanel checkInPanel;


    public CheckInForm() {
        // 1. Cargamos los datos en las JList al iniciar
        loadLists();

        // 2. Evento del botón (Ya lo tienes parcialmente planteado)
        realizarCheckInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarCheckIn();
            }
        });

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
                if (rsR.getString("status").charAt(0) == 'D') {
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

    private void ejecutarCheckIn() {
        Customer cliente = (Customer) list1.getSelectedValue();
        Room habitacion = (Room) list2.getSelectedValue();

        if (cliente == null || habitacion == null) {
            JOptionPane.showMessageDialog(null, "Selecciona un cliente y una habitación.");
            return;
        }

        try {
            // Creamos la entidad Book (Reserva)
            // Nota: Ajusta las fechas según necesites o usa un selector de fecha
            Book nuevaReserva = new Book();
            nuevaReserva.setCustomerDni(cliente.getDni());
            nuevaReserva.setRoomId(habitacion.getRoomNumber());
            nuevaReserva.setDateEntry("2026-05-14"); // Fecha actual de ejemplo
            nuevaReserva.setDateLeave("2026-05-15");
            nuevaReserva.setPurchaseStatus('P'); // Pendiente

            BookDAO dao = new BookDAO(nuevaReserva);
            dao.addBook();

            // OPCIONAL: Actualizar el estado de la habitación a 'O' (Ocupada)
            habitacion.setStatus('O');
            RoomDAO rDao = new RoomDAO(habitacion);
            rDao.updateRoom();

            JOptionPane.showMessageDialog(null, "Check-in realizado correctamente.");

            // Refrescamos las listas para que la habitación ocupada ya no aparezca
            loadLists();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public JPanel getCheckInPanel() {
        return checkInPanel;
    }
}
