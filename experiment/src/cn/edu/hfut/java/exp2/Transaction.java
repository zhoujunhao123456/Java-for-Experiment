package cn.edu.hfut.java.exp2;

import cn.edu.hfut.java.exp2.Record.ErrorRecord;
import cn.edu.hfut.java.exp2.Record.ShippingRecord;

public abstract class Transaction {
    public abstract int priority();
    public abstract void act(Inventory inventory);
}

class OrderTransaction extends Transaction {
    @Override
    public int priority() {
        return 3;
    }

    @Override
    public void act(Inventory inventory) {
        Item item=inventory.getItem(itemNumber);
        if(item==null)
            return;
        if(item.getQuantity()<quantity){
            inventory.getErrorRecords().add(new ErrorRecord(itemNumber,quantity,custom));
            return;
        }
        inventory.addShippingRecord(new ShippingRecord(itemNumber,quantity,custom));
        item.setQuantity(item.getQuantity()-quantity);
    }

    String custom;
    String itemNumber;
    int quantity;

    public OrderTransaction(String itemNumber, int quantity,String custom) {
        this.custom = custom;
        this.itemNumber = itemNumber;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return super.toString()+" "+custom+" "+itemNumber+" "+quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}

class ReceiveTransaction extends Transaction {
    @Override
    public int priority() {
        return 2;
    }

    @Override
    public void act(Inventory inventory) {
        Item item=inventory.getItem(itemNumber);
        if(item==null)
            return;
        item.setQuantity(item.getQuantity()+quantity);
    }

    String itemNumber;
    int quantity;

    public ReceiveTransaction(String itemNumber, int quantity) {
        this.itemNumber = itemNumber;
        this.quantity = quantity;
    }
}

class AddTransaction extends Transaction {
    @Override
    public int priority() {
        return 1;
    }

    @Override
    public void act(Inventory inventory) {
        inventory.getItems().add(new Item(itemNumber,0,supplier,description));
    }

    String itemNumber;
    String supplier;
    String description;

    public AddTransaction(String itemNumber, String supplier, String description) {
        this.itemNumber = itemNumber;
        this.supplier = supplier;
        this.description = description;
    }
}

class DeleteTransaction extends Transaction {
    @Override
    public int priority() {
        return 4;
    }

    @Override
    public void act(Inventory inventory) {
        Item item=inventory.getItem(itemNumber);
        if(item.getQuantity()>0) {
            inventory.getErrorRecords().add(new ErrorRecord(itemNumber, item.getQuantity()));
            return;
        }
        inventory.getItems().remove(item);
    }

    String itemNumber;

    public DeleteTransaction(String itemNumber) {
        this.itemNumber = itemNumber;
    }
}
