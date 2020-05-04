package com.example.builders_buddy.RecView;

public class InvoiceItemRecView {
    private String material;
    private String qty;
    private String price;


    public InvoiceItemRecView(String materil, String qty, String price) {
        this.setMaterial(materil);
        this.setQty(qty);
        this.setPrice(price);

    } public InvoiceItemRecView() {

    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}



