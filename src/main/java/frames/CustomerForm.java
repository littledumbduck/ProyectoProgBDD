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

                // 2. VALIDACIÓN AVANZADA (Acumulando errores)
                StringBuilder errores = new StringBuilder(); // Aquí apuntaremos todos los fallos

                // A) Campos obligatorios
                if (dni.isEmpty() || name.isEmpty() || surname.isEmpty()) {
                    errores.append("- Faltan campos obligatorios (DNI, Nombre o Apellidos).\n");
                }

                // B) DNI (Solo lo comprobamos si ha escrito algo, para no repetir el error de arriba)
                if (!dni.isEmpty() && !dni.matches("^[0-9]{8}[a-zA-Z]$")) {
                    errores.append("- El DNI debe tener 8 números y 1 letra (Ej: 12345678X).\n");
                }

                // C) Teléfono (Opcional, pero si hay texto, debe ser válido)
                if (!phone.isEmpty() && !phone.matches("^[0-9]{9}$")) {
                    errores.append("- El teléfono debe contener exactamente 9 números.\n");
                }

                // D) Email (Opcional, pero si hay texto, debe tener formato correo)
                if (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)\\.(.+)$")) {
                    errores.append("- El correo electrónico no tiene un formato válido.\n");
                }

                // E) EL JUEZ FINAL: ¿Hay algún error anotado?
                if (errores.length() > 0) {
                    // Si hay errores, mostramos la lista completa y cortamos la ejecución
                    JOptionPane.showMessageDialog(null,
                            "Por favor, corrige los siguientes datos antes de guardar:\n\n" + errores.toString(),
                            "Errores en el formulario",
                            JOptionPane.WARNING_MESSAGE);
                    return;
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
