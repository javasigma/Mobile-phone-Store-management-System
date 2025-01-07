package mobile;

import java.util.Random;

public class MobilePhone {
    private int id;
    private String brand;
    private String model;
    private int quantity;
    private double price;

    public MobilePhone(String brand, String model, int quantity, double price) {
        this.id = new Random().nextInt(100); // Random ID less than 100
        this.brand = brand;
        this.model = model;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Brand: " + brand + ", Model: " + model + ", Quantity: " + quantity + ", Price: " + price;
    }
}
