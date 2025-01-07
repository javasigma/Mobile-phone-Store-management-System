package mobile;


public class Main {
    public static void main(String[] args) {
        // Ensure that the GUI is initialized on the Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Instantiate and display the GUI
                new MobilePhoneShopGUI();
            }
        });
    }
}
