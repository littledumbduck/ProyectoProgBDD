package entities;

import utilities.ConexionMySQL;

import java.sql.SQLException;

public class Book {
    private int idBook;
    private String customerDni;
    private int roomId;
    private String dateEntry;
    private String dateLeave;
    private char purchaseStatus;
    private ConexionMySQL sql;

    // Constructor
    public Book(int idBook, String customerDni, int roomId, String dateEntry, String dateLeave, char purchaseStatus) {
        this.idBook = idBook;
        this.customerDni = customerDni;
        this.roomId = roomId;
        this.dateEntry = dateEntry;
        this.dateLeave = dateLeave;
        this.purchaseStatus = purchaseStatus;
    }

    // Getters/Setters
    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getCustomerDni() {
        return customerDni;
    }

    public void setCustomerDni(String customerDni) {
        this.customerDni = customerDni;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(String dateEntry) {
        this.dateEntry = dateEntry;
    }

    public String getDateLeave() {
        return dateLeave;
    }

    public void setDateLeave(String dateLeave) {
        this.dateLeave = dateLeave;
    }

    public char getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(char purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    // Functions

    public void addBook(String customerDni, int roomId, String dateEntry,
                        String dateLeave, char purchaseStatus) throws SQLException {

        sql = new ConexionMySQL();

        String sentence = "INSERT INTO book (customer_dni, room_id, dateEntry, dateLeave, purchaseStatus) VALUES ("
                + "'" + customerDni + "', "
                + roomId + ", "
                + "'" + dateEntry + "', "
                + "'" + dateLeave + "', "
                + (purchaseStatus == '1' || purchaseStatus == 'T' ? 1 : 0) + ")";

        sql.ejecutarInsertDeleteUpdate(sentence);

    }

}
