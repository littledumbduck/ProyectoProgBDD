package entities;

public class Customer {
    private String dni;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;

    // Constructor
    public Customer(String dni, String nombre, String apellidos, String email, String telefono) {
        this.dni = dni;
        this.name = nombre;
        this.surname = apellidos;
        this.email = email;
        this.phoneNumber = telefono;
    }

    public Customer() {
        this.dni = "";
        this.name = "";
        this.surname = "";
        this.email = "";
        this.phoneNumber = "";
    }

    // Getters + Setters


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name + " " + surname + " (" + dni + ")";
    }
}
