package entities;

public class Room {
    private int roomNumber;
    private String roomType;
    private int price;
    private char status;

    // Constructor
    public Room(int roomNumber, String roomType, int price, char status) {
        try {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.price = price;

            status = Character.toLowerCase(status);

            if (status == 'd' || status == 'o' || status == 'm') {
                this.status = status;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Getters/Setters


    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    // Functions



}
