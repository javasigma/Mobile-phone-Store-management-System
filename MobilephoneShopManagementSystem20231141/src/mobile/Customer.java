package mobile;

import java.util.Random;

public class Customer {
    private int id;
    private String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.id = new Random().nextInt(100); // Random ID less than 100
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Phone Number: " + phoneNumber;
    }
}
