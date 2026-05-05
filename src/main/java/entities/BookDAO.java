package entities;

import utilities.ConexionMySQL;

import java.sql.SQLException;

public class BookDAO {
    private Book book;
    private ConexionMySQL sql;

    public BookDAO (Book book) {
        this.book = book;
        this.sql = new ConexionMySQL();
    }

    public void addBook(String customerDni, int roomId, String dateEntry,
                        String dateLeave, char purchaseStatus) throws SQLException {

        // Objeto para hacer sentencias de SQL
        sql = new ConexionMySQL();

        // Cadena para meter la sentencia en la BD
        String sentence = "INSERT INTO book (customer_dni, room_id, dateEntry, dateLeave, purchaseStatus) VALUES ("
                + "'" + this.book.getCustomerDni() + "', "
                + this.book.getRoomId() + ", "
                + "'" + this.book.getDateEntry() + "', "
                + "'" + this.book.getDateLeave() + "', "
                + (this.book.getPurchaseStatus() == '1' || this.book.getPurchaseStatus() == 'T' ? 1 : 0) + ")";

        // Función para ejecutar la sentencia
        sql.ejecutarInsertDeleteUpdate(sentence);

    }
}
