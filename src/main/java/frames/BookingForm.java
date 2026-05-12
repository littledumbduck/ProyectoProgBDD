package frames;

import DAO.BookDAO;
import entities.Book;

import javax.swing.*;
import java.sql.SQLException;

public class BookingForm {
    private JPanel bookingPanel;
    private JTable bookingTable;
    private JTextField txtBookId;
    private JTextField txtCustomerDni;
    private JTextField txtRoomId;
    private JTextField txtDateEntry;
    private JTextField txtDateLeave;
    private JTextField txtPurchaseStatus;
    private JButton btnAddBooking;

    public BookingForm() {
        btnAddBooking.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // 1. Recogemos los datos
                String bookId = txtBookId.getText();
                String dni = txtCustomerDni.getText();
                String roomId = txtRoomId.getText();
                String dateEntry = txtDateEntry.getText();
                String dateLeave = txtDateLeave.getText();
                String purchaseStatus = txtPurchaseStatus.getText();

                // 2. Validación básica
                if (dni.isEmpty() || roomId.isEmpty() || dateEntry.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, rellena al menos DNI, Habitación y Fecha de Entrada.",
                            "Campos vacíos",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 3. Mensaje de éxito temporal
                String mensaje = "¡Reserva lista para guardar!\n" +
                        "DNI Cliente: " + dni + "\n" +
                        "ID Habitación: " + roomId;

                JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 4. USAMOS EL DAO PARA GUARDAR EN LA BASE DE DATOS

                Book booking = new Book(bookId, dni, roomId, dateEntry, dateLeave, purchaseStatus);
                BookDAO bookingDAO = new BookDAO(booking);

                try {
                    int roomId = Integer.parseInt(roomIdStr);

                    bookingDAO.addBook();

                    JOptionPane.showMessageDialog(null, "¡Reserva guardada con éxito en la base de datos!");

                    // 5. Limpiamos las cajas
                    txtBookId.setText("");
                    txtCustomerDni.setText("");
                    txtRoomId.setText("");
                    txtDateEntry.setText("");
                    txtDateLeave.setText("");
                    txtPurchaseStatus.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "El número de habitación debe ser un número válido.",
                            "Error de formato",
                            JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error al guardar en la base de datos: " + ex.getMessage(),
                            "Error SQL",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public JPanel getBookingPanel() {
        return bookingPanel;
    }
}
