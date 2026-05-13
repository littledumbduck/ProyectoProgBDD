package frames;

import DAO.RoomDAO;
import entities.Room;

import javax.swing.*;
import java.sql.SQLException;

public class RoomForm {
    private JPanel roomPanel;
    private JTable roomTable;
    private JTextField txtRoomNumber;
    private JTextField txtRoomFloor;
    private JTextField txtPrice;
    private JTextField txtRoomType;
    private JTextField txtRoomStatus;
    private JButton btnAddRoom;

    // --- CONSTRUCTOR CON LA LÓGICA DEL BOTÓN ---
    public RoomForm() {
        btnAddRoom.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // 1. Recoger datos
                String roomNumber = txtRoomNumber.getText();
                String floor = txtRoomFloor.getText();
                String type = txtRoomType.getText();
                String price = txtPrice.getText();
                String status = txtRoomStatus.getText();

                // 2. Validación básica
                if (roomNumber.isEmpty() || type.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, rellena al menos el Número, Tipo y Precio.",
                            "Campos vacíos",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 3. Mensaje de éxito
                String mensaje = "¡Habitación lista para guardar!\n" +
                        "Número: " + roomNumber + " (" + type + ")";

                JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 4. USAMOS EL DAO PARA GUARDAR EN LA BASE DE DATOS

                // Creamos los objetos haciendo el parseo de tus variables directamente
                Room room = new Room(
                        Integer.parseInt(roomNumber),
                        Integer.parseInt(floor),
                        type,
                        Double.parseDouble(price),
                        status.charAt(0)
                );

                RoomDAO roomDAO = new RoomDAO(room);

                // 5. TRY EXCLUSIVO PARA SQL
                try {
                    roomDAO.addRoom();

                    JOptionPane.showMessageDialog(null, "¡Habitación guardada con éxito en la base de datos!");

                    // 6. Limpiar cajas
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

                // 5. Limpiar cajas
                txtRoomNumber.setText("");
                txtRoomFloor.setText("");
                txtRoomType.setText("");
                txtPrice.setText("");
                txtRoomStatus.setText("");
            }
        });
    }

    // --- GETTER PARA EL MAIN ---
    public JPanel getRoomPanel() {
        return roomPanel;
    }
}
