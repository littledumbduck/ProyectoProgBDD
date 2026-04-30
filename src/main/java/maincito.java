import java.sql.ResultSet;
import java.sql.SQLException;

public class maincito {

    public static void main(String[] args) {
        ConexionMySQL X = new ConexionMySQL("root", "", "dam");
        try {
            X.conectar();

            String busqueda = "SELECT * FROM room";
            ResultSet datos = X.ejecutarSelect(busqueda);

            while (datos.next()) {
                int nombre = datos.getInt("roomNumber");
                System.out.print(nombre);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
