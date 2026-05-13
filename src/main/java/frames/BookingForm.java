package frames;

import DAO.BookDAO;
import entities.Book;

import javax.swing.*;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

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
        // Cargamos la tabla al abrir la ventana
        cargarTabla();

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

                // 2. VALIDACIÓN AVANZADA (Acumulando errores)
                StringBuilder errores = new StringBuilder();

                // A) Obligatorios
                if (dni.isEmpty() || roomId.isEmpty() || dateEntry.isEmpty()) {
                    errores.append("- Faltan campos obligatorios (DNI, Habitación o Fecha de Entrada).\n");
                }

                // B) Formato del DNI del Cliente (Clave foránea)
                if (!dni.isEmpty() && !dni.matches("^[0-9]{8}[a-zA-Z]$")) {
                    errores.append("- El DNI del cliente debe tener 8 números y 1 letra.\n");
                }

                // C) Formato de Números (Habitación y BookID)
                if (!roomId.isEmpty() && !roomId.matches("^[0-9]+$")) {
                    errores.append("- El número de habitación debe contener solo números.\n");
                }
                if (!bookId.isEmpty() && !bookId.matches("^[0-9]+$")) {
                    errores.append("- El ID de la reserva (si se rellena) debe ser numérico.\n");
                }

                // D) Formato de Fechas para MySQL (AAAA-MM-DD)
                String regexFecha = "^\\d{4}-\\d{2}-\\d{2}$";
                if (!dateEntry.isEmpty() && !dateEntry.matches(regexFecha)) {
                    errores.append("- La fecha de entrada debe tener formato AAAA-MM-DD (Ej: 2026-05-13).\n");
                }
                if (!dateLeave.isEmpty() && !dateLeave.matches(regexFecha)) {
                    errores.append("- La fecha de salida debe tener formato AAAA-MM-DD.\n");
                }

                // E) EL JUEZ FINAL: ¿Hay errores?
                if (errores.length() > 0) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, corrige los siguientes datos antes de guardar:\n\n" + errores.toString(),
                            "Errores en el formulario",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 3. PREPARAMOS VARIABLES
                int idParsed = 0;
                int roomParsed = 0;
                char statusParsed = ' ';

                // Como el Regex ya nos ha asegurado que son números limpios, los parseInt no fallarán
                if (!bookId.isEmpty()) {
                    idParsed = Integer.parseInt(bookId);
                }
                roomParsed = Integer.parseInt(roomId);

                if (!purchaseStatus.isEmpty()) {
                    statusParsed = purchaseStatus.toUpperCase().charAt(0);
                }

                // 4. USAMOS EL DAO PARA GUARDAR EN LA BASE DE DATOS
                Book booking = new Book(idParsed, dni, roomParsed, dateEntry, dateLeave, statusParsed);
                BookDAO bookingDAO = new BookDAO(booking);

                try {
                    bookingDAO.addBook();

                    JOptionPane.showMessageDialog(null, "¡Reserva guardada con éxito en la base de datos!");

                    // 5. Limpiamos las cajas
                    txtBookId.setText("");
                    txtCustomerDni.setText("");
                    txtRoomId.setText("");
                    txtDateEntry.setText("");
                    txtDateLeave.setText("");
                    txtPurchaseStatus.setText("");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error al guardar en la base de datos: " + ex.getMessage(),
                            "Error SQL",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // MÉTODO PARA LLENAR LA TABLA DE RESERVAS
    public void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("DNI Cliente");
        modelo.addColumn("Habitación");
        modelo.addColumn("Entrada");
        modelo.addColumn("Salida");
        modelo.addColumn("Estado");

        BookDAO dao = new BookDAO();
        try {
            ResultSet rs = dao.getAllBooks();
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("idBook");
                fila[1] = rs.getString("customer_dni");
                fila[2] = rs.getInt("room_id");
                fila[3] = rs.getString("dateEntry");
                fila[4] = rs.getString("dateLeave");
                fila[5] = rs.getString("purchaseStatus");
                modelo.addRow(fila);
            }
            bookingTable.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar reservas: " + ex.getMessage());
        }
    }

    public JPanel getBookingPanel() {
        return bookingPanel;
    }
}