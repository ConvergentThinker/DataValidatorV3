package org.main.jTable.Rule4;

public class Item {

    private String name;
    private double unitPrice;

    public Item(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", unitPrice=" + unitPrice + "]";
    }





}
