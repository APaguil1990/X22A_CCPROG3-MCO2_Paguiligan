// import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

/**
 * The main class to run the Vending Machine Factory Simulator.
 */
public class VendingMachineMain2 {
    /**
     * Main method to start the Vending Machine Factory Simulator.
     * 
     * @param args Comman-line arguments (not used in program)
     */
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();

        JFrame frame = new JFrame("Vending Machine Factory Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(3, 1));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton createVendingMachineButton = new JButton("Create a Vending Machine");
        JButton testVendingMachineButton = new JButton("Test a Vending Machine");
        JButton exitButton = new JButton("Exit");

        createVendingMachineButton.addActionListener(e -> {
            int machineChoice = JOptionPane.showOptionDialog(frame,
            "Choose the type of vending machien to create:", "Create a Vending Machine",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
            new String[] {"Regular Vending Machine", "Special Vending Machine"}, "Regular Vending Machine");

            if (machineChoice == JOptionPane.YES_OPTION) {
                vendingMachine.createRegularVendingMachine();
                JOptionPane.showMessageDialog(frame, "Regular Vending Machine Created.");
            }

            else if (machineChoice == JOptionPane.NO_OPTION) {
                vendingMachine.createSpecialVendingMachine();
                JOptionPane.showMessageDialog(frame, "Special Vending Machine Created.");
            }

            else {
                JOptionPane.showMessageDialog(frame, "Invalid choice. Please try again.");
            }
        });

        testVendingMachineButton.addActionListener(e -> {
            vendingMachine.testVendingMachine();
            JOptionPane.showMessageDialog(frame, "Test Vending Machine");
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you for using the vending machine factory simulator.");
            System.exit(0);
        });

        menuPanel.add(createVendingMachineButton);
        menuPanel.add(testVendingMachineButton);
        menuPanel.add(exitButton);

        frame.add(menuPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        // while (true) {
        //     // Main menu of the program
        //     System.out.println();
        //     System.out.println("Vending Machine Factory Simulator");
        //     System.out.println("==================================");
        //     System.out.println("[1] Create a Vending Machine");
        //     System.out.println("[2] Test a Vending Machine");
        //     System.out.println("[3] Exit");
        //     System.out.println("==================================");
        //     int choice = scanner.nextInt();

        //     switch (choice) {
        //         // Prompts the user to either create a regular or special vending machine
        //         case 1: {
        //             System.out.println();
        //             System.out.println("==================================");
        //             System.out.println("[1] Regular Vending Machine");
        //             System.out.println("[2] Special Vending Machine");
        //             System.out.println("==================================");
        //             System.out.printf("Enter your choice: ");
        //             int machineChoice = scanner.nextInt();

        //             if (machineChoice == 1) {
        //                 vendingMachine.createRegularVendingMachine(scanner);
        //                 System.out.println("Regular Vending Machine Created.");
        //             } else if (machineChoice == 2) {
        //                 vendingMachine.createSpecialVendingMachine(scanner);
        //                 System.out.println("Special Vending Machine Created.");
        //             } else {
        //                 System.out.println("Invalid choice. Please try again.");
        //             }
        //             break;
        //         }

        //         // Allows the user to test a vending machine
        //         case 2: {
        //             vendingMachine.testVendingMachine(scanner);
        //             break;
        //         }

        //         // Exit the program
        //         case 3: {
        //             System.out.println();
        //             System.out.println("Thank you for using the vending machine simulator. See you next time");
        //             System.exit(0);
        //         }

        //         // Default error message for incorrect inputs
        //         default: {
        //             System.out.println("Invalid choice. Please try again.");
        //             break;
        //         }
        //     }
        // }
    }
}
