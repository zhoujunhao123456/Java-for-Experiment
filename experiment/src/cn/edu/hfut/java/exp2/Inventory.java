package cn.edu.hfut.java.exp2;

import cn.edu.hfut.java.exp2.Record.ErrorRecord;
import cn.edu.hfut.java.exp2.Record.ShippingRecord;

import java.io.*;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private ArrayList<Transaction> transactions;
    private ArrayList<ErrorRecord> errorRecords=new ArrayList<>();
    private ArrayList<ShippingRecord> shippingRecords=new ArrayList<>();

    public ArrayList<ErrorRecord> getErrorRecords() {
        return errorRecords;
    }

    public void addShippingRecord(ShippingRecord shippingRecord) {
        for(ShippingRecord r:shippingRecords){
            if(r.equals(shippingRecord)){
                r.merge(shippingRecord);
                return;
            }
        }
        shippingRecords.add(shippingRecord);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(String itemNumber) {
        for (Item item : items) {
            if(String.valueOf(item.getItemNumber()).equals(itemNumber))
                return item;
        }
        return null;
    }

    public void executeTransactions() {
        transactions.forEach(t->t.act(this));
    }

    public void readItems(String filename) throws IOException {
        items = new ArrayList<Item>();
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.lines().forEach(line -> {
            String[] split = line.split("\t");
            Item i = new Item(split[0], Integer.parseInt(split[1]), split[2], split[3]);
            items.add(i);
        });
        br.close();
        items.sort((i1,i2)->{
            return i1.getItemNumber()-i2.getItemNumber();
        });
    }

    public void readTransactions(String filename) throws IOException {
        transactions = new ArrayList<>();
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.lines().forEach(line -> {
            transactions.add(TransactionFactory.createTransaction(line));
        });
        br.close();

        transactions.sort((t1, t2) -> {
            if ((t1 instanceof OrderTransaction) && (t2 instanceof OrderTransaction)) {
                return ((OrderTransaction) t1).quantity - ((OrderTransaction) t2).quantity;
            }
            return t1.priority() - t2.priority();
        });
    }

    public void saveNewInventory(String newInventory) throws IOException {
        items.sort((i1,i2)->{
            return i1.getItemNumber()-i2.getItemNumber();
        });
        File file = new File(newInventory);
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        for (Item item : items) {
            pw.println(item);
        }
        pw.close();
    }

    public void saveErrorRecords(String errors) throws IOException {
        File file = new File(errors);
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        for (ErrorRecord r : errorRecords) {
            pw.println(r);
        }
        pw.close();
    }

    public void saveShippingRecords(String shippings) throws IOException {
        File file = new File(shippings);
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        for(ShippingRecord s:shippingRecords){
            pw.println(s);
        }
        pw.close();
    }
}