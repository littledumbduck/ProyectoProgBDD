package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

public class ConexionMySQL {

    private String BD;
    private String USUARIO;
    private String PASS;
    private String HOST;
    private Connection connection;
    private TimeZone zonahoraria;

    /**
     * Constructor modificado: ahora llama a cargarConfiguracion()
     */
    public ConexionMySQL() {
        cargarConfiguracion();
        this.connection = null;
    }

    /**
     * Nuevo metodo para leer el archivo .properties
     */
    private void cargarConfiguracion() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("database.properties")) {
            prop.load(fis);

            // Asignamos los valores del archivo a nuestras variables
            this.HOST = prop.getProperty("db.host", "localhost");
            this.USUARIO = prop.getProperty("db.user", "root");
            this.PASS = prop.getProperty("db.pass", "");
            this.BD = prop.getProperty("db.name", "test");

        } catch (IOException e) {
            System.err.println("Ojo: No se pudo leer database.properties, usando valores por defecto.");
            // Valores por defecto si el archivo falla
            this.HOST = "localhost";
            this.USUARIO = "root";
            this.PASS = "";
            this.BD = "dam";
        }
    }

    private void registrarDriver() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al conectar con MySQL: " + e.getMessage());
        }
    }

    public void conectar() throws SQLException {
        if (connection == null || connection.isClosed()) {
            registrarDriver();
            Calendar now = Calendar.getInstance();
            zonahoraria = now.getTimeZone();

            // La URL ahora es dinámica según lo que leyó del archivo
            String url = "jdbc:mysql://" + HOST + "/" + BD +
                    "?useLegacyDatetimeCode=false&serverTimezone=" + zonahoraria.getID();

            connection = DriverManager.getConnection(url, USUARIO, PASS);
        }
    }

    public void desconectar() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public ResultSet ejecutarSelect(String consulta) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(consulta);
    }

    public int ejecutarInsertDeleteUpdate(String consulta) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(consulta);
    }
}