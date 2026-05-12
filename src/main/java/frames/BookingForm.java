package frames;

import javax.swing.*;

public class BookingForm {
    private JPanel bookingPanel;
    private JTable bookingTable;
    private JTextField txtIdBook;
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
                String dni = txtCustomerDni.getText();
                String roomId = txtRoomId.getText();
                String dateEntry = txtDateEntry.getText();
                String dateLeave = txtDateLeave.getText();

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

                // TODO: Aquí irá el código del BookDAO para guardar en la BD

                // 4. Limpiar cajas
                txtCustomerDni.setText("");
                txtRoomId.setText("");
                txtDateEntry.setText("");
                txtDateLeave.setText("");
            }
        });
    }

    public JPanel getBookingPanel() {
        return bookingPanel;
    }
}
