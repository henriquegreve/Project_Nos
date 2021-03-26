package com.greve.Model;

public class DonationMade {

    private long id;
    private long idFamily;
    private long idCampaign;
    private String observacao;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(long idFamily) {
        this.idFamily = idFamily;
    }

    public long getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(long idCampaign) {
        this.idCampaign = idCampaign;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
