package frames;

import javax.swing.*;

public class CustomerForm {
    private JPanel customerPanel;
    private JTable customerTable;
    private JButton btnAddCustomer;
    private JLabel DNI;
    private JTextField txtDni;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtEmail;
    private JTextField txtPhone;

    // --- AQUÍ EMPIEZA EL CONSTRUCTOR QUE AÑADE LA LÓGICA AL BOTÓN ---
    public CustomerForm() {
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // 1. Recogemos lo que el usuario ha escrito en las cajas
                String dni = txtDni.getText();
                String name = txtName.getText();
                String surname = txtSurname.getText();
                String email = txtEmail.getText();
                String phone = txtPhone.getText();

                // 2. Validación básica: comprobar que los campos clave no estén vacíos
                if (dni.isEmpty() || name.isEmpty() || surname.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, rellena al menos DNI, Nombre y Apellidos.",
                            "Campos vacíos",
                            JOptionPane.WARNING_MESSAGE);
                    return; // Cortamos la ejecución aquí para que no siga intentando guardar
                }

                // 3. Mensaje de prueba para confirmar que la interfaz funciona
                String mensaje = "¡Datos listos para enviar a la Base de Datos!\n" +
                        "DNI: " + dni + "\n" +
                        "Cliente: " + name + " " + surname;

                JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // TODO: Aquí el turbo añadirá su código del CustomerDAO en el futuro

                // 4. Limpiamos las cajas de texto después de simular el guardado
                txtDni.setText("");
                txtName.setText("");
                txtSurname.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
            }
        });
    }
    // --- AQUÍ TERMINA EL CONSTRUCTOR ---


    public JPanel getCustomerPanel() {
        return customerPanel;
    }
}
