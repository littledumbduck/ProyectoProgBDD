package frames;

import DAO.CustomerDAO;
import entities.Customer;

import javax.swing.*;
import java.sql.SQLException;

public class CustomerForm {
    private JPanel customerPanel;
    private JTable customerTable;
    private JButton btnAddCustomer;
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

                // 4. USAMOS EL DAO PARA GUARDAR EN LA BASE DE DATOS

                Customer customer = new Customer(dni, name, surname, email, phone);
                CustomerDAO customerDAO = new CustomerDAO(customer);

                try {
                    customerDAO.addCustomer();

                    // Si llegamos aquí, es que se guardó bien
                    JOptionPane.showMessageDialog(null, "¡Cliente guardado con éxito en la base de datos!");

                    // 4. Limpiamos las cajas de texto después de simular el guardado
                    txtDni.setText("");
                    txtName.setText("");
                    txtSurname.setText("");
                    txtEmail.setText("");
                    txtPhone.setText("");

                } catch (SQLException ex) {
                    // Si hay un error (DNI duplicado, conexión perdida...), saltará aquí
                    JOptionPane.showMessageDialog(null,
                            "Error al guardar en la base de datos: " + ex.getMessage(),
                            "Error SQL",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    // --- AQUÍ TERMINA EL CONSTRUCTOR ---


    public JPanel getCustomerPanel() {
        return customerPanel;
    }
}
