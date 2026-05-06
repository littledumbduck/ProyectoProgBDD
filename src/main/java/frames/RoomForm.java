package frames;

import javax.swing.*;

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

                // TODO: Aquí irá el código del RoomDAO de alturbo

                // 4. Limpiar cajas
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
