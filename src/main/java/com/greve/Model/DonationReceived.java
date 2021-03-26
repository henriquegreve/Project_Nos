package com.greve.Model;

import java.util.Date;

public class DonationReceived {

    private long id;
    private long idDonor;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdDonor() {
        return idDonor;
    }

    public void setIdDonor(long idDonor) {
        this.idDonor = idDonor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
