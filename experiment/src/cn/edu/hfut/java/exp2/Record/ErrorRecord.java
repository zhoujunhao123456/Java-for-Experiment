package cn.edu.hfut.java.exp2.Record;

public class ErrorRecord {
    private String number;
    private int quantity;
    private String custom;
    public ErrorRecord(String number, int quantity, String custom) {
        this.number = number;
        this.quantity = quantity;
        this.custom = custom;
    }
    public ErrorRecord(String number, int quantity) {
        this(number, quantity, "0");
    }

    @Override
    public String toString() {
        return custom+"\t"+number+"\t"+quantity;
    }
}
