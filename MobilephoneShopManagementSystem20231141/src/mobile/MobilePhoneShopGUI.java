package mobile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MobilePhoneShopGUI {
    private JFrame frame;
    private JTextArea textArea;
    private ShopInventory shop;

    public MobilePhoneShopGUI() {
        // Initialize the shop inventory with some sample data
        shop = new ShopInventory();
        shop.addMobilePhone("Apple", "iPhone 14", 10, 999.99);
        shop.addMobilePhone("Samsung", "Galaxy S22", 15, 799.99);
        shop.addMobilePhone("Huawei", "P40 Pro", 5, 899.99);
        shop.addCustomer("Alice", "1234567890");
        shop.addCustomer("Bob", "0987654321");

        // Setup JFrame and components
        frame = new JFrame("Mobile Phone Shop Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        // Welcome panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.CYAN);  // Set background color for the welcome panel
        JLabel welcomeLabel = new JLabel("Welcome to Mobile Phone Shop student id 20231141!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));  // Set a fancy font
        welcomePanel.add(welcomeLabel);
        frame.add(welcomePanel, BorderLayout.NORTH);

        // Text Area for displaying inventory and results
        textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create the main panel for buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10));  // Grid with 3 rows and 2 columns, spacing

        // Create individual panels for each button section with colors
        JPanel phonesPanel = createButtonPanel("Display Phones", Color.PINK);
        JPanel customersPanel = createButtonPanel("Display Customers", Color.green);
        JPanel sellPhonePanel = createButtonPanel("Sell Phone", Color.cyan);
        JPanel addPhonePanel = createButtonPanel("Add Phone", Color.ORANGE);
        JPanel addCustomerPanel = createButtonPanel("Add Customer", Color.YELLOW);
        JPanel searchPhonePanel = createButtonPanel("Search Phone", Color.pink);
        JPanel searchCustomerPanel = createButtonPanel("Search Customer", Color.LIGHT_GRAY);

        // Add panels to the main panel
        mainPanel.add(phonesPanel);
        mainPanel.add(customersPanel);
        mainPanel.add(sellPhonePanel);
        mainPanel.add(addPhonePanel);
        mainPanel.add(addCustomerPanel);
        mainPanel.add(searchPhonePanel);
        mainPanel.add(searchCustomerPanel);

        // Add the main panel to the frame
        frame.add(mainPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Helper method to create a button panel with color
    private JPanel createButtonPanel(String buttonText, Color panelColor) {
        JPanel panel = new JPanel();
        panel.setBackground(panelColor);
        JButton button = new JButton(buttonText);
        panel.add(button);

        // Add action listeners to each button
        switch (buttonText) {
            case "Display Phones":
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        displayPhones();
                    }
                });
                break;
            case "Display Customers":
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        displayCustomers();
                    }
                });
                break;
            case "Sell Phone":
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sellPhone();
                    }
                });
                break;
            case "Add Phone":
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addPhone();
                    }
                });
                break;
            case "Add Customer":
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addCustomer();
                    }
                });
                break;
            case "Search Phone":
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        searchPhone();
                    }
                });
                break;
            case "Search Customer":
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        searchCustomer();
                    }
                });
                break;
        }
        return panel;
    }

    // Display the list of phones in the inventory
    private void displayPhones() {
        List<MobilePhone> phones = shop.getMobilePhoneInventory();
        StringBuilder sb = new StringBuilder("Mobile Phone Inventory:\n");
        for (MobilePhone phone : phones) {
            sb.append(phone).append("\n");
        }
        textArea.setText(sb.toString());
    }

    // Display the list of customers in the inventory
    private void displayCustomers() {
        List<Customer> customers = shop.getCustomerInventory();
        StringBuilder sb = new StringBuilder("Customer Inventory:\n");
        for (Customer customer : customers) {
            sb.append(customer).append("\n");
        }
        textArea.setText(sb.toString());
    }

    // Sell a phone to a customer
    private void sellPhone() {
        try {
            String phoneIdInput = JOptionPane.showInputDialog("Enter Phone ID to sell:");
            int phoneId = Integer.parseInt(phoneIdInput);

            String customerName = JOptionPane.showInputDialog("Enter Customer Name:");
            String quantityInput = JOptionPane.showInputDialog("Enter Quantity:");
            int quantity = Integer.parseInt(quantityInput);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(frame, "Quantity must be a positive integer.");
                return;
            }

            Bill bill = shop.sellMobilePhone(phoneId, customerName, quantity);
            if (bill != null) {
                textArea.setText("Sale Successful!\n" + bill);
            } else {
                JOptionPane.showMessageDialog(frame, "Sale failed. Check phone ID or customer name.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid number format. Please enter valid numbers.");
        }
    }

    // Add a new phone to the inventory
    private void addPhone() {
        String brand = JOptionPane.showInputDialog("Enter Phone Brand:");
        String model = JOptionPane.showInputDialog("Enter Phone Model:");

        String quantityInput = JOptionPane.showInputDialog("Enter Quantity:");
        try {
            int quantity = Integer.parseInt(quantityInput);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(frame, "Quantity must be a positive integer.");
                return;
            }

            String priceInput = JOptionPane.showInputDialog("Enter Price:");
            double price = Double.parseDouble(priceInput);
            if (price <= 0) {
                JOptionPane.showMessageDialog(frame, "Price must be greater than 0.");
                return;
            }

            shop.addMobilePhone(brand, model, quantity, price);
            JOptionPane.showMessageDialog(frame, "New phone added successfully!");
            displayPhones();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid number format for quantity or price.");
        }
    }

    // Add a new customer to the inventory
    private void addCustomer() {
        String name = JOptionPane.showInputDialog("Enter Customer Name:");
        String phoneNumber = JOptionPane.showInputDialog("Enter Customer Phone Number:");

        shop.addCustomer(name, phoneNumber);
        JOptionPane.showMessageDialog(frame, "New customer added successfully!");
        displayCustomers();
    }

    // Search for a phone by ID
    private void searchPhone() {
        try {
            String phoneIdInput = JOptionPane.showInputDialog("Enter Phone ID to search:");
            int phoneId = Integer.parseInt(phoneIdInput);

            MobilePhone phone = shop.searchMobilePhoneById(phoneId);
            if (phone != null) {
                textArea.setText("Phone Found:\n" + phone);
            } else {
                JOptionPane.showMessageDialog(frame, "Phone not found with ID: " + phoneId);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid phone ID format.");
        }
    }

    // Search for a customer by name
    private void searchCustomer() {
        String name = JOptionPane.showInputDialog("Enter Customer Name to search:");
        Customer customer = shop.searchCustomerByName(name);
        if (customer != null) {
            textArea.setText("Customer Found:\n" + customer);
        } else {
            JOptionPane.showMessageDialog(frame, "Customer not found with name: " + name);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MobilePhoneShopGUI();  // Start the GUI
            }
        });
    }
}
