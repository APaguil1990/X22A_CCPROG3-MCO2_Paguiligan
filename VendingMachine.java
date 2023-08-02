import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.List;
import java.util.*;

/**
 * Represents a vending machine that sells items.
 */
public class VendingMachine {
    // Variables for regular vending machine items
    private JPanel itemInputPanel;
    private Item[] items;
    private int[] itemQuantities;
    private int[] itemPrices;
    private int[] itemCalories;
    private int[] itemSlots;
    private ArrayList<ArrayList<String>> itemProcessesList;
    private int change;
    private int[] startingInventory;
    private int[] endingInventory;
    private int totalAmountCollected;
    private int currentSlot;

    private JFrame vendingFrame;
    private JTextArea outputTextArea;
    private JFrame mainFrame;
    private JPanel productPanel;
    private JButton customizeButton;
    private JButton purchaseButton;
    private JTextField inputTextField;

    // Variables for special vending machine products
    private ArrayList<ArrayList<Item>> specialProducts;
    private ArrayList<String> productNames;
    private ArrayList<Integer> specialProductSlots;

    /**
     * Constructs a VendingMachine object with default values.
     */
    public VendingMachine() {
        items = new Item[10];
        itemQuantities = new int[10];
        itemPrices = new int[10];
        itemCalories = new int[10];
        itemSlots = new int[10];
        change = 0;
        startingInventory = new int[10];
        endingInventory = new int[10];
        totalAmountCollected = 0;
        currentSlot = 0;
    }

    /**
     * Creates a regular vending machine by taking input from the user.
     * 
     * 
     */
    public void createRegularVendingMachine() {
        itemProcessesList = new ArrayList<>();
        
        JFrame frame = new JFrame("Create Regular Vending Machine");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel changeLabel = new JLabel("Initial change amount:");
        JTextField changeField = new JTextField();

        JButton setChangeButton = new JButton("Set Change Amount");

        JTextArea statusArea = new JTextArea();
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(changeLabel);
        inputPanel.add(changeField);
        inputPanel.add(new JLabel());
        inputPanel.add(setChangeButton);

        setChangeButton.addActionListener(e -> {
            int initialChange = Integer.parseInt(changeField.getText());
            change = initialChange;

            statusArea.append("Initial change amount set to PHP " + change + "\n");

            inputPanel.setVisible(false);
            itemInputPanel.setVisible(true);

            changeField.setText("");
        });

        JLabel nameLabel = new JLabel("Item name:");
        JTextField nameField = new JTextField();

        JLabel priceLabel = new JLabel("Item price:");
        JTextField priceField = new JTextField();

        JLabel caloriesLabel = new JLabel("Item calories:");
        JTextField caloriesField = new JTextField();

        JLabel processesLabel = new JLabel("Enter the preparation processes for the item (comma-separated):");
        JTextField processesField = new JTextField();

        JButton addButton = new JButton("Add Item");
        addButton.setVisible(true);

        JPanel itemInputPanel = new JPanel(new GridLayout(5, 2));
        itemInputPanel.add(nameLabel);
        itemInputPanel.add(nameField);
        itemInputPanel.add(priceLabel);
        itemInputPanel.add(priceField);
        itemInputPanel.add(caloriesLabel);
        itemInputPanel.add(caloriesField);
        itemInputPanel.add(processesLabel);
        itemInputPanel.add(processesField);
        itemInputPanel.add(new JLabel());
        itemInputPanel.add(addButton);

        frame.add(itemInputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            String itemName = nameField.getText();
            int itemPrice = Integer.parseInt(priceField.getText());
            int itemCaloriesValue = Integer.parseInt(caloriesField.getText());
            String processesInput = processesField.getText();

            String[] processArray = processesInput.split(",");
            ArrayList<String> itemProcesses = new ArrayList<>();

            for (String process : processArray) {
                itemProcesses.add(process.trim());
            }

            Item item = new Item(itemName, itemPrice, 20, itemCaloriesValue, itemProcesses);
            int slot = currentSlot;

            items[slot] = item;
            itemQuantities[slot] = 20;
            itemPrices[slot] = itemPrice;
            itemCalories[slot] = itemCaloriesValue;
            itemProcessesList.add(itemProcesses);
            itemSlots[slot] = slot + 1;

            statusArea.append("Item added to slot " + itemSlots[slot] + ", Moving to next slot\n");

            if (itemSlots[slot] == items.length) {
                statusArea.append("All items added to the vending machine.\n");
                String inputAmount = JOptionPane.showInputDialog(frame, "Enter the amount of change to store in vending machine:");
                int generatedChange = Integer.parseInt(inputAmount);

                change += generatedChange;
                statusArea.append("Change in vending machine: PHP " + change);
                frame.dispose();
            }

            nameField.setText("");
            priceField.setText("");
            caloriesField.setText("");
            processesField.setText("");

            currentSlot++;
        });

        frame.setVisible(true);
        // System.out.println();
        // System.out.print("Enter the initial amount of change in the vending machine: ");
        // change = scanner.nextInt();
        // scanner.nextLine();
        
        // for (int i = 0; i < items.length; i++) {
        //     System.out.println();
        //     System.out.println("Enter the details for Item " + (i + 1) + ":");

        //     System.out.print("Item name: ");
        //     String itemName = scanner.nextLine();

        //     System.out.print("Item price: ");
        //     int itemPrice = scanner.nextInt();

        //     System.out.print("Item calories: ");
        //     int itemCaloriesValue = scanner.nextInt();

        //     scanner.nextLine();

        //     System.out.println("Enter the processes for " + itemName + " for the special vending machine:");
        //     String processesInput = scanner.nextLine();
        //     String[] processArray = processesInput.split(",");

        //     ArrayList<String> itemProcesses = new ArrayList<>();

        //     for (String process : processArray) {
        //         itemProcesses.add(process.trim());
        //     }

        //     Item item = new Item(itemName, itemPrice, 20, itemCaloriesValue, itemProcesses);
        //     items[i] = item;
        //     itemQuantities[i] = 20;
        //     itemPrices[i] = itemPrice;
        //     itemCalories[i] = itemCaloriesValue;
        //     itemProcessesList.add(itemProcesses);
        //     itemSlots[i] = i + 1;

        //     System.out.println("Item added to slot " + (i + 1) + ", " + "Moving to next slot");

        //     if (itemSlots[i] == items.length) {
        //         System.out.println();
        //         System.out.println("All items added to the vending machine.");
        //     }
        // }
        // System.out.println("Initial change amount set to PHP " + change);
        // System.out.println();
    }

    /**
     * Returns the current amount of change in the vending machine.
     * 
     * @return the amount of change in the vending machine
     */
    public int getChange() {
        return change;
    }

    /**
     * Displays the available inventory of the vending machine.
     */
    private void displayItems() {
        JFrame frame = new JFrame("Vending Machine Inventory");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLayout(new BorderLayout());

        JTextArea inventoryArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(inventoryArea);

        StringBuilder inventoryText = new StringBuilder();
        inventoryText.append("Vending Machine Inventory\n");
        inventoryText.append("==========================\n");

        for (int i = 0; i < itemSlots.length; i++) {
            Item item = items[i];
            inventoryText.append("Slot " + itemSlots[i] + ": " + item.getName() + " (" + item.getCalories() + " calories)" + ": PHP " + itemPrices[i] + " - " + itemQuantities[i] + " left");

            ArrayList<String> processes = itemProcessesList.get(i);

            if (!processes.isEmpty()) {
                inventoryText.append("Processes: \n" + String.join(", ", processes) + "\n");
            }
            inventoryText.append("\n");
        }

        inventoryText.append("\n");

        inventoryText.append("\nChange in Vending Machine: PHP " + getChange());

        inventoryArea.setText(inventoryText.toString());
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        // System.out.println("Vending Machine Inventory");
        // System.out.println("==========================");

        // for (int i = 0; i < itemSlots.length; i++) {
        //     Item item = items[i];
        //     System.out.println("Slot " + itemSlots[i] + ": " + item.getName() + " (" + item.getCalories() + " calories)" + ": PHP " + itemPrices[i] + " - " + itemQuantities[i] + " left");

        //     ArrayList<String> processes = itemProcessesList.get(i);

        //     if (!processes.isEmpty()) {
        //         System.out.println("Processes: \n" + String.join(", ", processes));
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // System.out.println("Change in Vending Machine: PHP " + getChange());
    }

    /**
     * Dispenses the specified amount of change to the user.
     * 
     * @param amount the amount of change to dispense
     */
    private void dispenseChange(int amount) {
        JFrame frame = new JFrame("Dispensing Change");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300,200);
        frame.setLayout(new BorderLayout());

        JTextArea changeArea = new JTextArea();
        changeArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(changeArea);

        StringBuilder changeText = new StringBuilder();
        changeText.append("Dispensing change: PHP " + amount + "\n");
        int numOfFifties = amount / 50;
        amount %= 50;
        int numOfTwenties = amount / 20;
        amount %= 20;
        int numOfTens = amount / 10;
        amount %= 10;
        int numOfFives = amount / 5;
        amount %= 5;
        int numOfOnes = amount / 1;

        changeText.append("Dispensing:\n");
        changeText.append(numOfFifties + " x PHP 50\n");
        changeText.append(numOfTwenties + " x PHP 20\n");
        changeText.append(numOfTens + " x PHP 10\n");
        changeText.append(numOfFives + " x PHP 5\n");
        changeText.append(numOfOnes + " x PHP 1\n");

        changeArea.setText(changeText.toString());
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        
        // System.out.println("Dispensing change: PHP " + amount);
        // int numOfFifties = amount / 50;
        // amount %= 50;
        // int numOfTwenties = amount / 20;
        // amount %= 20;
        // int numOfTens = amount / 10;
        // amount %= 10;
        // int numOfFives = amount / 5;
        // amount %= 5;
        // int numOfOnes = amount / 1;

        // System.out.println("Dispensing: ");
        // System.out.println(numOfFifties + " x PHP 50");
        // System.out.println(numOfTwenties + " x PHP 20");
        // System.out.println(numOfTens + " x PHP 10");
        // System.out.println(numOfFives + " x PHP 5");
        // System.out.println(numOfOnes + " x PHP 1");
    }

    /**
     * Test the vending machine by allowing the user to choose between the Regular Vending Machine or the Special Vending Machine.
     * 
     * @param scanner the Scanner object used for user input
     */
    public void testVendingMachine() {
        JFrame frame = new JFrame("Choose the Vending Machine to Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("Choose the Vending Machine to Test");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton regularMachineButton = new JButton("Regular Vending Machine");
        JButton specialMachineButton = new JButton("Special Vending Machine");
        JButton backButton = new JButton("Go back to main menu");

        regularMachineButton.addActionListener(e -> {
            frame.dispose();
            testRegularVendingMachine();
        });

        specialMachineButton.addActionListener(e -> {
            frame.dispose();
            testSpecialVendingMachine();
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            return;
        });

        mainPanel.add(titleLabel);
        mainPanel.add(regularMachineButton);
        mainPanel.add(specialMachineButton);
        mainPanel.add(backButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        // while (true) {
        //     System.out.println();
        //     System.out.println("Choose the Vending Machine to Test");
        //     System.out.println("===================================");
        //     System.out.println("[1] Regular Vending Machine");
        //     System.out.println("[2] Special Vending Machine");
        //     System.out.println("[3] Go back to main menu");
        //     System.out.println("===================================");
        //     System.out.printf("Enter your choice: ");
        //     int testChoice = scanner.nextInt();

        //     switch (testChoice) {
        //         case 1: {
        //             testRegularVendingMachine(scanner);
        //             break;
        //         }

        //         case 2: {
        //             testSpecialVendingMachine(scanner);
        //             break;
        //         }

        //         case 3: {
        //             return;
        //         }

        //         default: {
        //             System.out.println("Invalid choice. Please try again.");
        //             break;
        //         }
        //     }
        // }
    }

    /**
     * Tests the features of a regular vending machine.
     * 
     * @param scanner the Scanner object used for user input
     */
    public void testRegularVendingMachine() {
        JFrame frame = new JFrame("Choose the Feature You Want to Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("Choose the Feature You Want to Test");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton vendingFeaturesButton = new JButton("Vending Features");
        JButton maintenanceFeaturesButton = new JButton("Maintenance Features");
        JButton backButton = new JButton("Go back");

        vendingFeaturesButton.addActionListener(e -> {
            frame.dispose();
            testVendingFeatures();
        });

        maintenanceFeaturesButton.addActionListener(e -> {
            frame.dispose();
            testMaintenanceFeatures();
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            return;
        });

        mainPanel.add(titleLabel);
        mainPanel.add(vendingFeaturesButton);
        mainPanel.add(maintenanceFeaturesButton);
        mainPanel.add(backButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        // while (true) {
        //     System.out.println();
        //     System.out.println("Choose the Feature You Want to Test");
        //     System.out.println("====================================");
        //     System.out.println("[1] Vending Features");
        //     System.out.println("[2] Maintenance Features");
        //     System.out.println("[3] Go back to main menu");
        //     System.out.println("====================================");
        //     System.out.printf("Enter your choice: ");
        //     int featureTestChoice = scanner.nextInt();

        //     switch (featureTestChoice) {
        //         case 1: {
        //             testVendingFeatures(scanner);
        //             break;
        //         }

        //         case 2: {
        //             testMaintenanceFeatures(scanner);
        //             break;
        //         }

        //         case 3: {
        //             return;
        //         }

        //         default: {
        //             System.out.println("Invalid choice. Please try again.");
        //             break;
        //         }
        //     }
        // }
    }

    /**
     * Tests the features of the vending machine by allowing the user to make purchases.
     * 
     * @param scanner the Scanner object used for user input
     */
    private void testVendingFeatures() {
        JFrame frame = new JFrame("Vending Machine Features");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,400);
        frame.setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePurchase();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(purchaseButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // vendingFrame = frame;
        updateDisplayItems();
        frame.setVisible(true);

        // System.out.println();

        // if (countItemsInRegularVendingMachine() == 0) {
        //     System.out.println("There are no products in regular vending machines at present.");
        //     return;
        // }

        // System.out.println("Vending Features");
        // System.out.println("=================");
        // int totalQuantityPurchased = 0;
        // int totalChange = 0;
        // int totalAmountFromUser = 0;
        // List<String> purchasedItems = new ArrayList<>();

        // while (true) {
        //     displayItems();

        //     System.out.println("Enter the slot number of the item you want to purchase.");
        //     int slotNumber = scanner.nextInt();

        //     if (slotNumber >= 1 && slotNumber <= items.length) {
        //         int itemIndex = slotNumber - 1;
        //         Item selectedItem = items[itemIndex];

        //         if (itemQuantities[itemIndex] == 0) {
        //             System.out.println("Sorry, this product is out of stock! Please choose another item.");
        //         } else {
        //             System.out.println("Item selected: " + selectedItem.getName());
        //             System.out.println("Price: " + itemPrices[itemIndex]);
        //             System.out.println("Calories: " + selectedItem.getCalories());

        //             System.out.println("Enter the quantity to purchase: ");
        //             int quantity = scanner.nextInt();

        //             if (quantity > itemQuantities[itemIndex]) {
        //                 System.out.println("Insufficient quantity available. Please choose a lower quantity.");
        //             } else {
        //                 int totalPrice = itemPrices[itemIndex] * quantity;

        //                 System.out.println("Total price: PHP " + totalPrice);

        //                 System.out.println("Enter the amount to pay in PHP: ");
        //                 int amount = scanner.nextInt();

        //                 if (amount < totalPrice) {
        //                     System.out.println("Insufficient amount. Please enter the right amount.");
        //                 } else {
        //                     int change = amount - totalPrice;

        //                     itemQuantities[itemIndex] -= quantity;
        //                     totalAmountCollected += amount;
        //                     this.change -= change;

        //                     totalQuantityPurchased += quantity;
        //                     totalChange += change;
        //                     totalAmountFromUser += amount;

        //                     purchasedItems.add(selectedItem.getName() + " x " + quantity);

        //                     System.out.println("Items dispensed: " + selectedItem.getName() + " x " + quantity);
        //                     System.out.println("Change: PHP " + change);

        //                     System.out.println("Do you want to make another purchase? (Y/N)");
        //                     String choice = scanner.next();
                                
        //                     if (choice.equalsIgnoreCase("N")) {
        //                         break;
        //                     }
        //                 }
        //             }
        //         }
        //     } else if (slotNumber == 0) {
        //         break;
        //     } else {
        //         System.out.println("Invalid slot number. Please try again.");
        //     }
        // }
        // printTransactionSummary(purchasedItems);
        // System.out.println();
        // System.out.println("Total items quantity purhcased: " + totalQuantityPurchased);
        // System.out.println("Total amount collected from user: PHP " + totalAmountFromUser);
        // System.out.println();
        // dispenseChange(totalChange);
    }

    private void handlePurchase() {
        StringBuilder purchaseSummary = new StringBuilder();
        int totalQuantityPurchased = 0;
        int totalChange = 0;
        int totalAmountFromUser = 0;
        List<String> purchasedItems = new ArrayList<>();

        while (true) {
            int slotNumber = Integer.parseInt(JOptionPane.showInputDialog(vendingFrame, "Enter the slot number of the item you want to purchase:"));

            if (slotNumber >= 1 && slotNumber <= items.length) {
                int itemIndex = slotNumber - 1;
                Item selectedItem = items[itemIndex];

                if(itemQuantities[itemIndex] == 0) {
                    JOptionPane.showMessageDialog(vendingFrame, "Sorry, this item is out of stock.", "Out of Stock", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog(vendingFrame, "Item selected: " + selectedItem.getName() + "\nPrice: " + itemPrices[itemIndex] + "\nCalories: " + selectedItem.getCalories() + "\nEnter the quantity to purchase:"));

                    if (quantity > itemQuantities[itemIndex]) {
                        JOptionPane.showMessageDialog(vendingFrame, "Insufficient quantity available.", "Insufficient Quantity", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int totalPrice = itemPrices[itemIndex] * quantity;

                        int amount = Integer.parseInt(JOptionPane.showInputDialog(vendingFrame, "Total price: PHP " + totalPrice + "\nEnter the amount to pay in PHP:"));

                        if (amount < totalPrice) {
                            JOptionPane.showMessageDialog(vendingFrame, "Insufficient amount. Please enter valid amount.", "Insufficient Amount", JOptionPane.WARNING_MESSAGE);
                        } else {
                            int change = amount - totalPrice;

                            itemQuantities[itemIndex] -= quantity;
                            totalAmountCollected += amount;
                            this.change -= change;

                            totalQuantityPurchased += quantity;
                            totalChange += change;
                            totalAmountFromUser += amount;

                            purchasedItems.add(selectedItem.getName() + " x " + quantity);

                            purchaseSummary.append("Items dispensed: ").append(selectedItem.getName()).append(" x ").append(quantity).append("\nChange: PHP ").append(change).append("\n");

                            int continuePurchase = (JOptionPane.showConfirmDialog(vendingFrame, "Do you want to make another purchase?", "Continue Purchase", JOptionPane.YES_NO_OPTION));

                            if (continuePurchase == JOptionPane.NO_OPTION) {
                                break;
                            }
                        }
                    }
                }
            } else if (slotNumber == 0) {
                break;
            } else {
                JOptionPane.showMessageDialog(vendingFrame, "Invalid slot number.");
            }
        }

        printTransactionSummary(purchasedItems);

        if (totalChange > 0) {
            dispenseChange(totalChange);
        }
        JTextArea purchaseSummaryArea = new JTextArea();
        purchaseSummaryArea.setEditable(false);
        purchaseSummaryArea.setText(purchaseSummary.toString());
        JScrollPane summaryScrollPane = new JScrollPane(purchaseSummaryArea);

        JFrame summaryFrame = new JFrame("Purchase Summary");
        summaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        summaryFrame.setSize(400,300);
        summaryFrame.setLayout(new BorderLayout());
        summaryFrame.add(summaryScrollPane, BorderLayout.CENTER);
        summaryFrame.setVisible(true);
    }

    private void updateDisplayItems() {
        StringBuilder inventoryText = new StringBuilder();
        inventoryText.append("Vending Machine Inventory\n");
        inventoryText.append("==========================\n");

        for (int i = 0; i < itemSlots.length; i++) {
            Item item = items[i];
            inventoryText.append("Slot ").append(itemSlots[i]).append(": ").append(item.getName()).append(" (").append(item.getCalories()).append(" calories)").append(": PHP ").append(itemPrices[i]).append(" - ").append(itemQuantities[i]).append(" left");

            ArrayList<String> processes = itemProcessesList.get(i);

            if (!processes.isEmpty()) {
                inventoryText.append("Processes: \n").append(String.join(", ", processes)).append("\n");
            }
            inventoryText.append("\n");
        }

        inventoryText.append("\nChange in Vending Machine: PHP ").append(getChange());

        outputTextArea.setText(inventoryText.toString());
    }

    /**
     * Prints a summary of the transaction including purchased items and total amount.
     * 
     * @param purchasedItems the list of purchased items
     */
    private void printTransactionSummary(List<String> purchasedItems) {
        StringBuilder summaryText = new StringBuilder();
        summaryText.append("Transaction Summary\n");
        summaryText.append("===================\n");

        if (purchasedItems.isEmpty()) {
            summaryText.append("No items purchased.\n");
        } else {
            summaryText.append("Items Purchased:\n");
            
            int totalAmount = 0;

            for (int i = 0; i < purchasedItems.size(); i++) {
                String item = purchasedItems.get(i);
                String[] itemDetails = item.split(" x ");
                String itemName = itemDetails[0];
                int quantity = Integer.parseInt(itemDetails[1]);

                int itemIndex = getItemIndexByName(itemName);
                int price = itemPrices[itemIndex];
                int itemAmount = price * quantity;

                summaryText.append(itemName).append(" x ").append(quantity).append(" - Price: PHP ").append(price * quantity).append("\n");
                totalAmount += itemAmount;
            }
            summaryText.append("Total Amount: PHP ").append(totalAmount).append("\n");

        // System.out.println();
        // System.out.println("Transaction Summary");
        // System.out.println("====================");

        // if (purchasedItems.isEmpty()) {
        //     System.out.println("No items purchased.");
        // } else {
        //     System.out.println("Items Purchased:");

        //     int totalAmount = 0;

        //     for (int i = 0; i < purchasedItems.size(); i++) {
        //         String item = purchasedItems.get(i);
        //         String[] itemDetails = item.split(" x ");
        //         String itemName = itemDetails[0];
        //         int quantity = Integer.parseInt(itemDetails[1]);

        //         int itemIndex = getItemIndexByName(itemName);
        //         int price = itemPrices[itemIndex];
        //         int itemAmount = price * quantity;

        //         System.out.println(itemName + " x " + quantity + " - Price: PHP " + (price * quantity));
        //         totalAmount += itemAmount;
        //     }
        //     System.out.println("Total Amount: PHP " + totalAmount);
        // }
        }
        JOptionPane.showMessageDialog(null, summaryText.toString(), "Transaction Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Returns the index of the item with the specified name.
     * 
     * @param itemName the name of the item
     * @return the index of the item, -1 if index is not found
     */
    private int getItemIndexByName(String itemName) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName().equals(itemName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Tests the maintenance features of the vending machine.
     * 
     * @param scanner the Scanner object used for user input
     */
     private void testMaintenanceFeatures() {
        JFrame maintenanceFrame = new JFrame("Maintenance Features");
        maintenanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        maintenanceFrame.setSize(400,300);
        maintenanceFrame.setLayout(new GridLayout(5, 1));

        JButton restockButton = new JButton("Restock Items");
        JButton setPriceButton = new JButton("Set Item Price");
        JButton collectMoneyButton = new JButton("Collect Money");
        JButton replenishMoneyButton = new JButton("Replenish Money");
        JButton backButton = new JButton("Go back");

        restockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintenanceFrame.dispose();
                restockItems();
            }
        });

        setPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintenanceFrame.dispose();
                setItemPrice();
            }
        });

        collectMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintenanceFrame.dispose();
                collectMoney();
            }
        });

        replenishMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintenanceFrame.dispose();
                replenishMoney();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintenanceFrame.dispose();
                return;
            }
        });
        // System.out.println();

        // if (countItemsInRegularVendingMachine() == 0) {
        //     System.out.println("There are no products in regular vending machines at present.");
        //     return;
        // }

        // System.out.println("Maintenance Features");
        // System.out.println("========================");
        // System.out.println("[1] Restock Items");
        // System.out.println("[2] Set Item Price");
        // System.out.println("[3] Collect Money");
        // System.out.println("[4] Replenish Money");
        // System.out.println("[5] Go back to main menu");
        // System.out.println("========================");
        // System.out.printf("Enter you choice: ");
        // int maintenanceChoice = scanner.nextInt();

        // switch (maintenanceChoice) {
        //     case 1: {
        //         restockItems(scanner);
        //         break;
        //     }

        //     case 2: {
        //         setItemPrice(scanner);
        //         break;
        //     }

        //     case 3: {
        //         collectMoney();
        //         break;
        //     }

        //     case 4: {
        //         replenishMoney(scanner);
        //         break;
        //     }

        //     case 5: {
        //         return;
        //     }

        //     default: {
        //         System.out.println("Invalid choice. Please try again.");
        //         break;
        //     }
        // }
    }

    /**
     * Restocks the items in the vending machine.
     * 
     * @param scanner the Scanner object used for user input
     */
    private void restockItems() {
        System.out.println();
        // System.out.println("Restock Items");
        // System.out.println("==============");
        JOptionPane.showMessageDialog(null, "Restock Items\n===========");

        while (true) {
            displayItems();

            String slotNumberInput = JOptionPane.showInputDialog("Enter the slot number of the item you want to restock:");
            int slotNumber = Integer.parseInt(slotNumberInput);

            if (slotNumber >= 1 && slotNumber <= items.length) {
                int itemIndex = slotNumber - 1;
                Item selectedItem = items[itemIndex];

                if (itemQuantities[itemIndex] >= 20) {
                    JOptionPane.showMessageDialog(null, "Item still fully stocked.");
                } else {
                    String quantityInput = JOptionPane.showInputDialog("Enter the quantity to restock:");
                    System.out.println("Enter the quantity to restock: ");
                    int quantity = Integer.parseInt(quantityInput);

                    int availableQuantity = 20 - itemQuantities[itemIndex];
                    int restockQuantity = quantity;

                    if (restockQuantity > availableQuantity) {
                        restockQuantity = availableQuantity;
                        System.out.println("Note: Only " + availableQuantity + " units are available to restock for " + selectedItem.getName() + ".");
                    }
                    itemQuantities[itemIndex] += restockQuantity;
                    startingInventory[itemIndex] += restockQuantity;
                    endingInventory[itemIndex] = itemQuantities[itemIndex];

                    // System.out.println("Item restocked: " + selectedItem.getName() + " (" + restockQuantity + ") ");
                    // System.out.println("Current quantity for " + selectedItem.getName() + ": " + itemQuantities[itemIndex]);

                    JOptionPane.showMessageDialog(null, "Item restocked: " + selectedItem.getName() + " (" + restockQuantity + ")");
                    JOptionPane.showMessageDialog(null, "Current quantity for " + selectedItem.getName() + ": " + itemQuantities[itemIndex]);
                }
                // System.out.println("Do you want to restock another item?");
                // String choice = scanner.next();
                int option = JOptionPane.showConfirmDialog(null, "Do you want to restock another item?", "Restock Item", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION) {
                    return;
                }

            } else if (slotNumber == 0) {
                return;
            } else {
                JOptionPane.showConfirmDialog(null, "Invalid slot number. Please try again.");
            }
        }
    }

    /**
     * Sets the price of an item in the vending machine.
     * 
     * @param scanner the Scanner object used for user input
     */
    private void setItemPrice() {
        JOptionPane.showMessageDialog(null, "Set Item Price\n===============");

        while (true) {
            displayItems();

            String slotNumberInput = JOptionPane.showInputDialog("Enter the slot number of the item you want to set the price for:");
            int slotNumber = Integer.parseInt(slotNumberInput);

            if (slotNumber >= 1 && slotNumber <= items.length) {
                int itemIndex = slotNumber - 1;
                Item selectedItem = items[itemIndex];

                String newPriceInput = JOptionPane.showInputDialog("Enter the new price for the item in PHP: ");
                int newPrice = Integer.parseInt(newPriceInput);

                itemPrices[itemIndex] = newPrice;
                JOptionPane.showMessageDialog(null, "Price set for " + selectedItem.getName() + ": PHP " + newPrice);
                
                int option = JOptionPane.showConfirmDialog(null, "Do you want to set the price of another item?", "Set Item Price", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION) {
                    return;
                }
            } else if (slotNumber == 0) {
                return;
            } else {
                System.out.println("Invalid slot number. Please try again.");
            }
        }
    }

    /**
     * Collects the money from the vending machine.
     */
    private void collectMoney() {
        JOptionPane.showMessageDialog(null, "Collect Money\n==============");

        if (totalAmountCollected == 0) {
            JOptionPane.showMessageDialog(null, "Error: No money has been collected.");
        } else {
            JOptionPane.showMessageDialog(null, "Total amount collected: PHP " + totalAmountCollected);
            totalAmountCollected = 0;
            JOptionPane.showMessageDialog(null, "Money collected. Total amount reset to 0.");
        } 
    }

    /**
     * Replenishes the amount of change in the vending machine by adding the specified amount.
     * 
     * @param scanner the Scanner object used for user input
     */
    private void replenishMoney() {
        JOptionPane.showMessageDialog(null, "Replenish Money\n================");

        String additionalChangeInput = JOptionPane.showInputDialog("Enter the amount of change to replenish in PHP: ");
        int additionalChange = Integer.parseInt(additionalChangeInput);

        change += additionalChange;

        JOptionPane.showMessageDialog(null, "Change replenished: PHP " + additionalChange);
        JOptionPane.showMessageDialog(null, "Current change amount: PHP " + change);
    }

    /**
     * Counts the number of items in the regular vending machine.
     * 
     * @return the number of items in the regular vending machine
     */
    private int countItemsInRegularVendingMachine() {
        int count = 0;
        for (Item item : items) {
            if (item != null) {
                count++;
            }
        }
        return count;
    }

    /**
    * Creates a special vending machine by taking input from the user.
    * 
    * @param scanner the Scanner object used for user input
    */
    public void createSpecialVendingMachine() {
        productNames = new ArrayList<>();
        specialProducts = new ArrayList<>();
        specialProductSlots = new ArrayList<>();

        JFrame frame = new JFrame("Create Special Vending Machine");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Product Name:");
        JTextField nameField = new JTextField();
        JButton addButton = new JButton("Add Product");
        JButton doneButton = new JButton("Done");

        addButton.addActionListener(e -> {
        String productName = nameField.getText().trim();

        if (!productName.isEmpty()) {
            productNames.add(productName);
            specialProducts.add(new ArrayList<Item>());
            specialProductSlots.add(productPanel.getComponentCount() + 1);

            productPanel.add(new JLabel("Product " + productName + " created. Added to slot " + specialProductSlots.get(specialProductSlots.size() - 1)));

            nameField.setText("");
            frame.pack();
        }
        });

        doneButton.addActionListener(e -> frame.dispose());

        inputPanel.add(nameLabel, BorderLayout.WEST);
        inputPanel.add(nameField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(productPanel), BorderLayout.CENTER);
        frame.add(doneButton, BorderLayout.SOUTH);

        frame.setVisible(true);

        // System.out.println("\nCreating Special Vending Machine");
        // System.out.println("===================================");

        // int slotNumber = 0;

        // while (true) {
        //     System.out.println();
        //     System.out.print("Enter the name of the product to add to the special vending machine (or 'done' to finish adding products): ");
        //     String productName = scanner.nextLine();

        //     if (productName.isEmpty()) {
        //         productName = scanner.nextLine();
        //     }

        //     if (productName.equalsIgnoreCase("done")) {
        //         break;
        //     }

        //     productNames.add(productName);
        //     specialProducts.add(new ArrayList<Item>());
        //     specialProductSlots.add(slotNumber++);

        //     System.out.println("\nProduct " + productName + " created. Added to slot " + slotNumber);
        // }

        // if (productNames.isEmpty()) {
        //     System.out.println("\nNo products added to the special vending machine.");
        // } else {
        //     System.out.println("\nAll products added to the special vending machine.");
        // }
    }

    /**
     * Displays the names of the products that were created in the special vending machine.
     * 
     * @param productNames the list of the names of the products created in the special vending machine.
     */
    private void displaySpecialProduct(ArrayList<String> productNames) {
        JFrame frame = new JFrame("Special Vending Machine Products");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(new BorderLayout());

        JList<String> productList = new JList<>(productNames.toArray(new String[0]));
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(productList);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
        // for (int i = 0; i < productNames.size(); i++) {
        //     String productName = productNames.get(i);
        //     int slotNumber = specialProductSlots.get(i);
        //     slotNumber++;

        //     System.out.println("Slot " + slotNumber + ": " + productName);
        // }
    }

    /**
     * Tests the special vending machine by allowing the user to customize any product with any items from
     * the regular vending machine and purchase that item. 
     * 
     * @param scanner the Scanner object used for user input
     */
    private void testSpecialVendingMachine() {
        int rvmItemsCount = countItemsInRegularVendingMachine();

        if (rvmItemsCount == 0) {
            JOptionPane.showMessageDialog(null, "There are no items in the regular vending machine.", "No Items", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        mainFrame = new JFrame("Special Vending Machine");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new BorderLayout());

        productPanel = new JPanel(new GridLayout(productNames.size() + 1, 3, 10, 5));

        JLabel productLabel = new JLabel("Special Vending Machine Products");
        productLabel.setFont(new Font("Arial", Font.BOLD, 16));
        productPanel.add(productLabel);
        productPanel.add(new JLabel()); 
        productPanel.add(new JLabel()); 

        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i);
            int slotNumber = specialProductSlots.get(i) + 1;

            JLabel productInfoLabel = new JLabel("Slot " + slotNumber + ": " + productName);
            productPanel.add(productInfoLabel);

            customizeButton = new JButton("Customize");
            customizeButton.setActionCommand(productName);
            customizeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showCustomizeDialog(e.getActionCommand());
                }
            });
            productPanel.add(customizeButton);

            purchaseButton = new JButton("Purchase");
            purchaseButton.setActionCommand(productName);
            purchaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    purchaseCustomProduct(e.getActionCommand());
                }
            });
            productPanel.add(purchaseButton);
        }

        mainFrame.add(productPanel, BorderLayout.NORTH);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        mainFrame.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        inputTextField = new JTextField();
        mainFrame.add(inputTextField, BorderLayout.SOUTH);

        mainFrame.setVisible(true);

        // System.out.println("\nSpecial Vending Machine Products");
        // System.out.println("=================================");

        // displaySpecialProduct(productNames);
        // System.out.println("\nChange in vending machine: " + change);
        
        // System.out.print("\nEnter the slot number of the product you want to customize (or 'done' to finish customizing product): ");
        // String input = scanner.nextLine();

        // if (input.isEmpty()) {
        //     input = scanner.nextLine();
        // }

        // while (!input.equalsIgnoreCase("done")) {
        //     int productIndex;

        //     try {
        //         productIndex = Integer.parseInt(input) - 1;
        //     } catch (NumberFormatException e) {
        //         System.out.println("Invalid input. Please enter a valid input.");
        //         input = scanner.nextLine();
        //         continue;
        //     }

        //     if (productIndex < 0 || productIndex >= productNames.size()) {
        //         System.out.println("Invalid slot number. Please try again.");
        //     } else {
        //         String productName = productNames.get(productIndex);
        //         ArrayList<Item> productItems = specialProducts.get(productIndex);

        //         System.out.println("\nCustomizing Product: " + productName);
        //         System.out.println("===================================");

        //         while (true) {
        //             displayItems();

        //             System.out.print("\nEnter the slot number of the item to add to the product (or 'done' to finish adding items): ");
        //             input = scanner.nextLine();

        //             if (input.equalsIgnoreCase("done")) {
        //                 break;
        //             }

        //             int slotNumber;

        //             try {
        //                 slotNumber = Integer.parseInt(input);
        //             } catch (NumberFormatException e) {
        //                 System.out.println("Invalid input. Please try again.");
        //                 continue;
        //             }

        //             if (slotNumber < 1 || slotNumber > items.length) {
        //                 System.out.println("Invalid slot number. Please try again.");
        //             } else {
        //                 int itemIndex = slotNumber - 1;
        //                 Item selectedItem = items[itemIndex];

        //                 if (itemQuantities[itemIndex] == 0) {
        //                     System.out.println("Sorry, this item is out of stock.");
        //                 } else {
        //                     System.out.print("Enter the quantity of " + selectedItem.getName() + " to add to the product: ");
        //                     int quantityToAdd = scanner.nextInt();
        //                     scanner.nextLine();

        //                     if (quantityToAdd <= 0 || quantityToAdd > itemQuantities[itemIndex]) {
        //                         System.out.println("Invalid quantity. Please enter valid input.");
        //                         continue;
        //                     }

        //                     for (int i = 0; i < quantityToAdd; i++) {
        //                         productItems.add(selectedItem);
        //                     }

        //                     System.out.println(quantityToAdd + " unit/s of " + selectedItem.getName() + " added to product");
        //                 }
        //             }
        //         }
        //     }

        //     System.out.println("\nEnter the slot number of the product you want to customize (or 'done' to finish customizing product): ");
        //     input = scanner.nextLine();
        // }

        // System.out.println("\nYour Customized Products");
        // System.out.println("===========================");

        // int customizedProductsCount = 0;
        // ArrayList<Integer> customizedProductIndexes = new ArrayList<>();

        // for (int i = 0; i < productNames.size(); i++) {
        //     String productName = productNames.get(i);
        //     ArrayList<Item> productItems = specialProducts.get(i);

        //     if (!productItems.isEmpty()) {
        //         customizedProductsCount++;
        //         customizedProductIndexes.add(i);

        //         int totalPrice = 0;
        //         int totalCalories = 0;

        //         System.out.println("Product: " + productName);
        //         System.out.println("\nIngredients list of: " + productName);

        //         ArrayList<String> uniqueItemNames = new ArrayList<>();
        //         ArrayList<Integer> itemQuantitiesList = new ArrayList<>();

        //         for (Item productItem : productItems) {
        //             String itemName = productItem.getName();
        //             int itemIndex = getItemIndexByName(itemName);

        //             if (itemIndex == -1) {
        //                 continue;
        //             }

        //             int exisitingItemIndex = uniqueItemNames.indexOf(itemName);

        //             if (exisitingItemIndex == -1) {
        //                 uniqueItemNames.add(itemName);
        //                 itemQuantitiesList.add(1);
        //             } else {
        //                 int currentQuantity = itemQuantitiesList.get(exisitingItemIndex);
        //                 itemQuantitiesList.set(exisitingItemIndex, currentQuantity + 1);
        //             }
        //         }

        //         for (int j = 0; j < uniqueItemNames.size(); j++) {
        //             String itemName = uniqueItemNames.get(j);
        //             int itemQuantity = itemQuantitiesList.get(j);
        //             int slotNumber = getItemIndexByName(itemName) + 1;
        //             int itemPrice = itemPrices[getItemIndexByName(itemName)];
        //             int itemCalories = items[slotNumber - 1].getCalories();

        //             int itemTotalPrice = itemQuantity * itemPrice;
        //             int itemTotalCalories = itemQuantity * itemCalories;

        //             System.out.println(itemName + " x " + itemQuantity + " - PHP " + itemQuantity * itemPrice + " (" + itemQuantity * itemCalories + " calories)");

        //             totalPrice += itemTotalPrice;
        //             totalCalories += itemTotalCalories;
        //         }

        //         System.out.println();
        //         System.out.println("Total Price: PHP " + totalPrice);
        //         System.out.println("Total Calories: " + totalCalories + " calories");
        //         System.out.println("==========================\n");
        //     }
        // }

        // ArrayList<String> purchasedProducts = new ArrayList<>();

        // if (customizedProductsCount == 0) {
        //     System.out.println("There are no customized products.");
        //     return;
        // } else if (customizedProductsCount == 1) {
        //     int selectProductIndex = customizedProductIndexes.get(0);
        //     String productName = productNames.get(selectProductIndex);

        //     System.out.print("Do you want to purchase this custom product? (Y/N): ");
        //     String purchaseChoice = scanner.nextLine();

        //     if (purchaseChoice.equalsIgnoreCase("Y")) {
        //         int totalPrice = calculateTotalPriceForProduct(selectProductIndex);
        //         purchaseCustomizedProduct(scanner, productName, totalPrice);
        //         purchasedProducts.add(productName);
        //     }
        // } else {
        //     System.out.print("Enter the slot number of the product you want to purchase: (or 'done' to finish): ");
        //     input = scanner.nextLine();

        //     if (input.isEmpty()) {
        //         input = scanner.nextLine();
        //     }

        //     while (!input.equalsIgnoreCase("done")) {
        //         int productIndex;

        //         try {
        //             productIndex = Integer.parseInt(input);
        //         } catch (NumberFormatException e) {
        //             System.out.println("Invalid input. Enter a valid input. ");
        //             input = scanner.nextLine();
        //             continue;
        //         }

        //         if (productIndex < 0 || productIndex >= customizedProductIndexes.size()) {
        //             System.out.println("Invalid slot number. Please try again.");
        //         } else {
        //             int selectProductIndex = customizedProductIndexes.get(productIndex);
        //             String productName = productNames.get(selectProductIndex);

        //             System.out.print("Do you want to purchase this custom product? (Y/N): ");
        //             String purchaseChoice = scanner.nextLine();

        //             if (purchaseChoice.equalsIgnoreCase("Y")) {
        //                 int totalPrice = calculateTotalPriceForProduct(selectProductIndex);
        //                 purchaseCustomizedProduct(scanner, productName, totalPrice);
        //                 purchasedProducts.add(productName);
        //             }
        //         }

        //         System.out.print("Enter the slot number of the product you want to purchase (or 'done' to finish): ");
        //         input = scanner.nextLine();
        //     }
        // }

        // printTransactionSummary(purchasedProducts);
    }

    /**
     * Displays a dialog for customizing a product in special vending machine
     * 
     * @param productName The name of the product to be customized
     */
    private void showCustomizeDialog(String productName) {
        ArrayList<Item> productItems = specialProducts.get(productNames.indexOf(productName));
        StringBuilder output = new StringBuilder();
    
        output.append("\nCustomizing Product: ").append(productName).append("\n");
        output.append("===================================\n");
    
        while (true) {
            displayItems() ;
            String input = JOptionPane.showInputDialog(mainFrame,"Available Items:\n" + "\nEnter the slot number of the item to add to the product\n(or 'done' to finish adding items):");
    
            if (input == null || input.equalsIgnoreCase("done")) {
                break;
            }
    
            try {
                int slotNumber = Integer.parseInt(input);
                if (slotNumber < 1 || slotNumber > items.length) {
                    JOptionPane.showMessageDialog(mainFrame, "Invalid slot number. Please try again.");
                    continue;
                }
    
                int itemIndex = slotNumber - 1;
                Item selectedItem = items[itemIndex];
    
                if (itemQuantities[itemIndex] == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "Sorry, this item is out of stock.");
                } else {
                    String quantityInput = JOptionPane.showInputDialog(mainFrame,
                            "Enter the quantity of " + selectedItem.getName() + " to add to the product:");
    
                    if (quantityInput == null) {
                        continue;
                    }
    
                    int quantityToAdd = Integer.parseInt(quantityInput);
    
                    if (quantityToAdd <= 0 || quantityToAdd > itemQuantities[itemIndex]) {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid quantity. Please enter valid input.");
                        continue;
                    }
    
                    for (int i = 0; i < quantityToAdd; i++) {
                        productItems.add(selectedItem);
                    }
    
                    output.append(quantityToAdd).append(" unit/s of ").append(selectedItem.getName()).append(" added to product\n");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainFrame, "Invalid input. Please enter a valid input.");
            }
        }
            int totalPrice = 0;
            int totalCalories = 0;

            for (Item productItem : productItems) {
                int itemIndex = getItemIndexByName(productItem.getName());

                if (itemIndex != -1) {
                    totalPrice += itemPrices[itemIndex];
                    totalCalories += productItem.getCalories();
                }
            }
            output.append("\nTotal Price: PHP ").append(totalPrice).append("\n");
            output.append("Total Calories: ").append(totalCalories).append(" calories\n");
            outputTextArea.append(output.toString());
    }

    /**
     * Allows the user to purchase any of the customized products from special vending machine
     * 
     * @param productName The name of the customized product to be purchased.
     */
    private void purchaseCustomProduct(String productName) {
        ArrayList<Item> productItems = specialProducts.get(productNames.indexOf(productName));
    
        String amountInput = JOptionPane.showInputDialog(mainFrame,
                "Enter amount to pay in PHP:");
        
        if (amountInput == null) {
            return;
        }
    
        int amount = Integer.parseInt(amountInput);
        int totalPrice = calculateTotalPriceForProduct(productNames.indexOf(productName));
    
        if (amount < totalPrice) {
            JOptionPane.showMessageDialog(mainFrame, "Insufficient amount. Payment canceled.");
        } else {
            int change = amount - totalPrice;
    
            totalAmountCollected += totalPrice;
            this.change -= change;
    
            ArrayList<String> purchasedProcesses = new ArrayList<>();
    
            for (Item productItem : productItems) {
                int itemIndex = getItemIndexByName(productItem.getName());
    
                if (itemIndex != -1) {
                    itemQuantities[itemIndex]--;
                    purchasedProcesses.add(productItem.getProcesses() + ": " + productItem.getName());
                }
            }
            
            StringBuilder output = new StringBuilder();
            output.append("\nProduct: ").append(productName).append(" done.\n");

            for (String process : purchasedProcesses) {
                output.append(process).append("\n");
            }
            
    
            // Print the transaction summary
            output.append("\nTransaction Summary\n");
            output.append("====================\n");
            for (String purchasedProduct : purchasedProcesses) {
                output.append("- ").append(purchasedProduct).append("\n");
            }
            output.append("====================\n");
    
            outputTextArea.append(output.toString());
    
            if (change > 0) {
                dispenseChange(change);
            }
        }
    }

    /**
     * Calculates the total price for a customized product.
     * 
     * @param productIndex the index of the customized product in the special vending machine
     * @return the total price of the customized product
     */
    private int calculateTotalPriceForProduct(int productIndex) {
        int totalPrice = 0;
        ArrayList<Item> productItems = specialProducts.get(productIndex);

        for (Item producItem : productItems) {
            String itemName = producItem.getName();
            int itemIndex = getItemIndexByName(itemName);

            if (itemIndex != -1) {
                int itemPrice = itemPrices[itemIndex];
                totalPrice += itemPrice;
            }
        }
        return totalPrice;
    }

    // /**
    //  * Allows the user to purchase a customized product.
    //  * 
    //  * @param scanner     The Scanner object used for user input
    //  * @param productName The name of the customized product
    //  * @param totalPrice  The total price of the customized product to be paid
    //  */
    // private void purchaseCustomizedProduct(Scanner scanner, String productName, int totalPrice) {
    //     System.out.print("Enter amount to pay in PHP: ");
    //     int amount = scanner.nextInt();
    //     scanner.nextLine();

    //     if (amount < totalPrice) {
    //         System.out.println("Insufficient amount. Payment canceled.");
    //     } else {
    //         int change = amount - totalPrice;

    //         totalAmountCollected += totalPrice;
    //         this.change -= change;

    //         ArrayList<Item> productItems = specialProducts.get(productNames.indexOf(productName));

    //         ArrayList<String> purchasedProcesses = new ArrayList<>();

    //         for (Item productItem : productItems) {
    //             int itemIndex = getItemIndexByName(productItem.getName());

    //             if (itemIndex != -1) {
    //                 itemQuantities[itemIndex]--;

    //                 purchasedProcesses.add(productItem.getProcesses() + ": " + productItem.getName());
    //             }
    //         }

    //         for (String process : purchasedProcesses) {
    //             System.out.println(process);
    //         }

    //         System.out.println("\nProduct: " + productName + " done.\n");

    //         if (change > 0) {
    //             dispenseChange(change);
    //         }
    //     }
    // }

    // /**
    //  * Prints the transaction summary for the purchased products
    //  * 
    //  * @param purchasedProducts The ArrayList containing the names of the purchased products.
    //  */
    // private void printTransactionSummary(ArrayList<String> purchasedProducts) {
    //     if (!purchasedProducts.isEmpty()) {
    //         System.out.println("\nTransaction Summary");
    //         System.out.println("====================");

    //         for (String purchasedProduct : purchasedProducts) {
    //             System.out.println("- " + purchasedProduct);
    //         }

    //         System.out.println("====================");
    //     }
    // }
}
