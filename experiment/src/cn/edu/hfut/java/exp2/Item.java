package cn.edu.hfut.java.exp2;

public class Item {
    private String itemNumber;
    private int quantity;
    private String supplier;
    private String description;

    public Item(String itemNumber, int quantity, String supplier, String description) {
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.supplier = supplier;
        this.description = description;
    }

    public int getItemNumber() {
        return Integer.parseInt(itemNumber);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return itemNumber + "\t" + quantity + "\t" + supplier + "\t" + description;
    }
}
