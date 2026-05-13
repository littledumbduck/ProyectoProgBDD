package entities;

public class Room {
    private int roomNumber;
    private int roomFloor;
    private String roomType;
    private double price;
    private char status;

    // Constructor
    public Room(int roomNumber, int roomFloor, String roomType, double price, char status) {
        this.roomNumber = roomNumber;
        this.roomFloor = roomFloor;
        this.roomType = roomType;
        this.price = price;

        // Variable para el estado de la habitación
        status = Character.toLowerCase(status);

        // Establece el estado
        possibleStatus(status);
    }

    public Room(int roomNumber, int roomFloor, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomFloor = roomFloor;
        this.roomType = roomType;
        this.price = price;
        this.status = 'd';
    }

    public Room() {
        this.roomNumber = 0;
        this.roomFloor = 0;
        this.roomType = "";
        this.price = 0;
        this.status = 'd';
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getStatus() {
        return status;
    }

    // Variables permitted: d/o/m
    public void setStatus(char status) {
        possibleStatus(status);
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

// Functions

    // Asigna un estado o devuelve una excepción
    private void possibleStatus(char status) {
        try {
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
}
