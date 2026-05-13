package frames;

import DAO.RoomDAO;
import entities.Room;

import javax.swing.*;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class RoomForm {
    private JPanel roomPanel;
    private JTable roomTable;
    private JTextField txtRoomNumber;
    private JTextField txtRoomFloor;
    private JTextField txtPrice;
    private JTextField txtRoomType;
    private JTextField txtRoomStatus;
    private JButton btnAddRoom;

public RoomForm() {

    cargarTabla();

    btnAddRoom.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            // 1. Recoger datos
            String roomNumber = txtRoomNumber.getText();
            String floor = txtRoomFloor.getText();
            String type = txtRoomType.getText();
            String price = txtPrice.getText();
            String status = txtRoomStatus.getText();

            // 2. VALIDACIÓN AVANZADA (Acumulando errores)
            StringBuilder errores = new StringBuilder();

            // A) Obligatorios
            if (floor.isEmpty() || type.isEmpty() || price.isEmpty()) {
                errores.append("- Faltan campos obligatorios (Planta, Tipo o Precio).\n");
            }

            // B) Formato Numérico (Habitación y Planta)
            if (!roomNumber.isEmpty() && !roomNumber.matches("^[0-9]+$")) {
                errores.append("- El número de habitación debe ser un valor numérico.\n");
            }
            if (!floor.isEmpty() && !floor.matches("^[0-9]+$")) {
                errores.append("- La planta debe ser un valor numérico.\n");
            }

            // C) Formato de Precio (Admite decimales con punto)
            if (!price.isEmpty() && !price.matches("^[0-9]+(\\.[0-9]{1,2})?$")) {
                errores.append("- El precio debe ser un número (puede usar un punto, ej: 45.50).\n");
            }

            // D) Formato de Tipo y Estado (Letras individuales)
            if (!type.isEmpty() && type.length() > 1) {
                errores.append("- El tipo de habitación debe ser una sola letra (Ej: S o D).\n");
            }
            if (!status.isEmpty() && status.length() > 1) {
                errores.append("- El estado de la habitación debe ser una sola letra.\n");
            }

            // E) EL JUEZ FINAL
            if (errores.length() > 0) {
                JOptionPane.showMessageDialog(null,
                        "Por favor, corrige los siguientes datos antes de guardar:\n\n" + errores.toString(),
                        "Errores en el formulario",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3. PREPARAMOS VARIABLES
            int numParsed = 0;
            char statusParsed = 'D'; // Si dejan el estado vacío, por defecto es Disponible ('D')

            if (!roomNumber.isEmpty()) {
                numParsed = Integer.parseInt(roomNumber);
            }

            int floorParsed = Integer.parseInt(floor);
            double priceParsed = Double.parseDouble(price);

            // Pasamos a mayúscula para que en la BD quede uniforme
            String typeParsed = type.toUpperCase();

            if (!status.isEmpty()) {
                statusParsed = status.toUpperCase().charAt(0);
            }

            // 4. USAMOS EL DAO PARA GUARDAR EN LA BASE DE DATOS
            Room room = new Room(numParsed, floorParsed, typeParsed, priceParsed, statusParsed);
            RoomDAO roomDAO = new RoomDAO(room);

            try {
                roomDAO.addRoom();

                JOptionPane.showMessageDialog(null, "¡Habitación guardada con éxito en la base de datos!");

                // 5. Limpiar cajas
                txtRoomNumber.setText("");
                txtRoomFloor.setText("");
                txtRoomType.setText("");
                txtPrice.setText("");
                txtRoomStatus.setText("");

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
        modelo.addColumn("Nº");
        modelo.addColumn("Planta");
        modelo.addColumn("Tipo");
        modelo.addColumn("Precio");
        modelo.addColumn("Estado");

        RoomDAO dao = new RoomDAO();
        try {
            ResultSet rs = dao.getAllRooms();
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("roomNumber");
                fila[1] = rs.getInt("roomfloor");
                fila[2] = rs.getString("roomType");
                fila[3] = rs.getDouble("price");
                fila[4] = rs.getString("status");
                modelo.addRow(fila);
            }
            roomTable.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar habitaciones: " + ex.getMessage());
        }
    }

public JPanel getRoomPanel() {
    return roomPanel;
}
}