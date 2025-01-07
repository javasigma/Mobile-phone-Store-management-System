package mobile;

public class Bill {
    private MobilePhone mobilePhone;
    private Customer customer;
    private int soldQuantity;
    private double totalPrice;

    public Bill(MobilePhone mobilePhone, Customer customer, int soldQuantity) {
        this.mobilePhone = mobilePhone;
        this.customer = customer;
        this.soldQuantity = soldQuantity;
        this.totalPrice = soldQuantity * mobilePhone.getPrice();
        // Update mobile phone quantity after the sale
        mobilePhone.setQuantity(mobilePhone.getQuantity() - soldQuantity);
    }

    public void displayBill() {
        System.out.println("Customer: " + customer.getName() + " (" + customer.getPhoneNumber() + ")");
        System.out.println("Mobile Phone: " + mobilePhone.getBrand() + " " + mobilePhone.getModel());
        System.out.println("Quantity Sold: " + soldQuantity);
        System.out.println("Total Price: " + totalPrice);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + "\nMobile Phone: " + mobilePhone.getBrand() + " " + mobilePhone.getModel() +
               "\nQuantity Sold: " + soldQuantity + "\nTotal Price: " + totalPrice;
    }
}

