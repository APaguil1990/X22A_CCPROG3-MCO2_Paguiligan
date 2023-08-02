import java.util.*;

/**
 * Represents an item in a vending machine.
 */
public class Item {
    private String name;
    private int price;
    private int quantity;
    private int calories;
    private ArrayList<String> processes;

    /**
     * Constructs an Item object with the specified name, price, quantity, and calories.
     * 
     * @param name      the name of the item
     * @param price     the price of the item in PHP
     * @param quantity  the quantity of the item available
     * @param calories  the number of calories for the item
     */
    public Item(String name, int price, int quantity, int calories, ArrayList<String> processes) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.calories = calories;
        this.processes = processes;
    }

    /**
     * Returns the name of the item.
     * 
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the item in PHP.
     *  
     * @return the price of the item in PHP.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the available quantity of the item.
     * 
     * @return the available quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the number of calories in the item.
     * 
     * @return the number of calories in the item
     */
    public int getCalories() {
        return calories;
    }

    public ArrayList<String> getProcesses() {
        return processes;
    }
}