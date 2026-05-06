package frames;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    // Este es el panel vacío creado en el Main.form
    private JPanel MainFrame;

    public static void main(String[] args) {
        // 1. Creamos la ventana principal
        JFrame frame = new JFrame("Sistema de Gestión de Reservas de Hotel");
        Main app = new Main();
        frame.setContentPane(app.MainFrame);

        // --- 2. CONSTRUYENDO LA BARRA DE MENÚ ---
        JMenuBar menuBar = new JMenuBar();

        // Categoría 1: Gestión (Clientes, Habitaciones, Reservas)
        JMenu menuGestion = new JMenu("Gestión");
        JMenuItem itemClientes = new JMenuItem("Gestión de Clientes");
        JMenuItem itemHabitaciones = new JMenuItem("Gestión de Habitaciones");
        JMenuItem itemReservas = new JMenuItem("Gestión de Reservas");

        menuGestion.add(itemClientes);
        menuGestion.add(itemHabitaciones);
        menuGestion.add(itemReservas);

        // Categoría 2: Operaciones (Check-in, Ingresos)
        JMenu menuOperaciones = new JMenu("Operaciones");
        JMenuItem itemCheckin = new JMenuItem("Realizar Check-in");
        JMenuItem itemIngresos = new JMenuItem("Consultar Ingresos");

        menuOperaciones.add(itemCheckin);
        menuOperaciones.add(itemIngresos);

        // Añadimos las categorías a la barra principal
        menuBar.add(menuGestion);
        menuBar.add(menuOperaciones);

        // Le decimos a la ventana que use esta barra de menú
        frame.setJMenuBar(menuBar);

        // --- 3. EVENTOS DE LOS BOTONES (Prueba) ---
        itemClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Creamos una nueva ventana emergente (JDialog)
                JDialog customerDialog = new JDialog(frame, "Gestión de Clientes", true);
                CustomerForm form = new CustomerForm();

                // Le metemos el panel que acabas de diseñar
                customerDialog.setContentPane(form.getCustomerPanel());
                // Ajustamos el tamaño para que no se vea minúscula
                customerDialog.setSize(600, 400);

                // Centramos la ventanita respecto a la ventana principal
                customerDialog.setLocationRelativeTo(frame);

                // La mostramos
                customerDialog.setVisible(true);
            }
        });

        // 4. Configuración final de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Tamaño inicial
        frame.setLocationRelativeTo(null); // Para que aparezca centrada en la pantalla
        frame.setVisible(true);
    }
}