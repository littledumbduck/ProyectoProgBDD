package entities;

public class Book {
    private int idBook;
    private Customer customer;
    private Room room;
    private String dateEntry;
    private String dateLeave;
    private char purchaseStatus;

    // Constructor
    public Book(int idBook, Customer customer, Room room, String dateEntry, String dateLeave, char purchaseStatus) {
        this.idBook = idBook;
        this.customer = customer;
        this.room = room;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
