package mobile;

import java.util.ArrayList;
import java.util.List;

public class ShopInventory {
    private List<MobilePhone> mobilePhones;
    private List<Customer> customers;
    private double totalEarnings;

    public ShopInventory() {
        this.mobilePhones = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.totalEarnings = 0.0;
    }

    // Add Mobile Phone
    public void addMobilePhone(String brand, String model, int quantity, double price) {
        MobilePhone newPhone = new MobilePhone(brand, model, quantity, price);
        mobilePhones.add(newPhone);
    }

    // Search Mobile Phone by ID
    public MobilePhone searchMobilePhoneById(int id) {
        for (MobilePhone phone : mobilePhones) {
            if (phone.getId() == id) {
                return phone;
            }
        }
        return null;
    }

    // Modify Mobile Phone by ID
    public boolean modifyMobilePhone(int id, String model, String brand, int quantity, double price) {
        MobilePhone phone = searchMobilePhoneById(id);
        if (phone != null) {
            phone.setQuantity(quantity);
            // Updating other fields if needed
            return true;
        }
        return false;
    }

    // Remove Mobile Phone by ID
    public boolean removeMobilePhoneById(int id) {
        MobilePhone phone = searchMobilePhoneById(id);
        if (phone != null) {
            mobilePhones.remove(phone);
            return true;
        }
        return false;
    }

    // Display Mobile Phone Inventory
    public List<MobilePhone> getMobilePhoneInventory() {
        return mobilePhones;
    }

    // Add Customer
    public void addCustomer(String name, String phoneNumber) {
        Customer newCustomer = new Customer(name, phoneNumber);
        customers.add(newCustomer);
    }

    // Search Customer by Name
    public Customer searchCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    // Display Customer Inventory
    public List<Customer> getCustomerInventory() {
        return customers;
    }

    // Sell Mobile Phone
    public Bill sellMobilePhone(int phoneId, String customerName, int quantity) {
        MobilePhone phone = searchMobilePhoneById(phoneId);
        Customer customer = searchCustomerByName(customerName);
        
        if (phone != null && customer != null && phone.getQuantity() >= quantity) {
            Bill bill = new Bill(phone, customer, quantity);
            totalEarnings += bill.getTotalPrice();
            return bill;
        } else {
            return null;
        }
    }

    // Get Total Earnings
    public double getTotalEarnings() {
        return totalEarnings;
    }
}
