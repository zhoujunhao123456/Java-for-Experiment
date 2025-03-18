package cn.edu.hfut.java.exp2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Inventory inventory = new Inventory();
        inventory.readItems("Inventory.txt");
        inventory.readTransactions("Transactions.txt");
        inventory.executeTransactions();
        inventory.saveNewInventory("NewInventory.txt");
        inventory.saveErrorRecords("Errors.txt");
        inventory.saveShippingRecords("Shippings.txt");

    }
}
