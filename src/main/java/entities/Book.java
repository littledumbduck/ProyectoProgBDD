package entities;

public class Book {
    private int idBook;
    private int idCustomer;
    private int idRoom;
    private String dateEntry;
    private String dateLeave;
    private char purchaseStatus;

    // Constructor
    public Book(int idBook, int idCustomer, int idRoom, String dateEntry, String dateLeave, char purchaseStatus) {
        this.idBook = idBook;
        this.idCustomer = idCustomer;
        this.idRoom = idRoom;
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

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
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
}
