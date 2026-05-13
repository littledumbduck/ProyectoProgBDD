package frames;

import DAO.CustomerDAO;
import entities.Customer;

import javax.swing.*;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class CustomerForm {
    private JPanel customerPanel;
    private JTable customerTable;
    private JButton btnAddCustomer;
    private JTextField txtDni;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JButton btnUpdateCustomer;

    // --- AQUÍ EMPIEZA EL CONSTRUCTOR QUE AÑADE LA LÓGICA AL BOTÓN ---
    public CustomerForm() {
        // Cargamos la tabla nada más arrancar la ventana
        cargarTabla();

        // --- 1. EVENTO PARA PASAR DATOS DE LA TABLA A LAS CAJAS ---
        customerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Averiguamos en qué fila ha hecho clic el usuario
                int fila = customerTable.getSelectedRow();

                // Si hay una fila seleccionada (índice 0 o mayor)
                if (fila >= 0) {
                    // Cogemos los datos de cada columna y los ponemos en su caja de texto correspondiente
                    txtDni.setText(customerTable.getValueAt(fila, 0).toString());
                    txtName.setText(customerTable.getValueAt(fila, 1).toString());
                    txtSurname.setText(customerTable.getValueAt(fila, 2).toString());

                    // Para email y teléfono, comprobamos que no sean nulos en la base de datos para evitar errores
                    Object emailObj = customerTable.getValueAt(fila, 3);
                    txtEmail.setText(emailObj != null ? emailObj.toString() : "");

                    Object phoneObj = customerTable.getValueAt(fila, 4);
                    txtPhone.setText(phoneObj != null ? phoneObj.toString() : "");

                    // NOTA: Para no liar a la base de datos, lo ideal es bloquear el DNI para que no lo cambien,
                    // ya que es la clave primaria. El usuario puede cambiar nombre, teléfono, etc.
                    txtDni.setEnabled(false);
                }
            }
        });

        // --- 2. LÓGICA DEL BOTÓN MODIFICAR ---
        // (Asegúrate de haber creado el botón btnUpdateCustomer en tu diseño visual)
        btnUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String dni = txtDni.getText();
                String name = txtName.getText();
                String surname = txtSurname.getText();
                String email = txtEmail.getText();
                String phone = txtPhone.getText();

                // Validaciones básicas (puedes añadir aquí el mismo bloque StringBuilder de errores que usaste para Guardar)
                if (dni.isEmpty() || name.isEmpty() || surname.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un cliente de la tabla y rellena sus datos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Creamos el objeto y el DAO
                Customer customer = new Customer(dni, name, surname, email, phone);
                CustomerDAO customerDAO = new CustomerDAO(customer);

                try {
                    // Usamos el método UPDATE que ya tienes programado en el DAO
                    customerDAO.updateCustomer();

                    JOptionPane.showMessageDialog(null, "¡Cliente actualizado correctamente!");

                    // Actualizamos la tabla para ver los cambios
                    cargarTabla();

                    // Limpiamos las cajas y volvemos a habilitar la caja del DNI para futuros nuevos clientes
                    txtDni.setText("");
                    txtName.setText("");
                    txtSurname.setText("");
                    txtEmail.setText("");
                    txtPhone.setText("");
                    txtDni.setEnabled(true);

                    // --- REHABILITAR CLAVE PRIMARIA ---
                    txtDni.setEnabled(true);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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

                    // Actualizamos la tabla
                    cargarTabla();

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

    // Añadimos el método cargarTabla() entero aquí, fuera del constructor
    public void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Email");
        modelo.addColumn("Teléfono");

        // Usamos el constructor vacío que añadimos en el DAO
        CustomerDAO dao = new CustomerDAO();

        try {
            ResultSet rs = dao.getAllCustomers();

            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("dni");
                fila[1] = rs.getString("name");
                fila[2] = rs.getString("surname");
                fila[3] = rs.getString("email");
                fila[4] = rs.getString("phonenumber");
                modelo.addRow(fila);
            }
            customerTable.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + ex.getMessage());
        }
    }


    public JPanel getCustomerPanel() {
        return customerPanel;
    }
}
