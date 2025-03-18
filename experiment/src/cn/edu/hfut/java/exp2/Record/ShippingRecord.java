package cn.edu.hfut.java.exp2.Record;

public class ShippingRecord {
    private String number;
    private int quantity;
    private String custom;
    public ShippingRecord(String number, int quantity, String custom) {
        this.number = number;
        this.quantity = quantity;
        this.custom = custom;
    }

    public boolean equals(ShippingRecord s) {
        return this.number.equals(s.number)&&this.custom.equals(s.custom);
    }

    public void merge(ShippingRecord s){
        this.quantity += s.quantity;
    }

    @Override
    public String toString() {
        return custom+"\t"+number+"\t"+quantity;
    }
}
