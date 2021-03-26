package com.greve.Model;

public class DonationsItems {

    private long id;
    private long idItem;
    private long idDonation;
    private int quantity;
    private int type; //0 = doação recebida || 1 = doação feita

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public long getIdDonation() {
        return idDonation;
    }

    public void setIdDonation(long idDonation) {
        this.idDonation = idDonation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
