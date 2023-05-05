package com.example.EpicDoodles;

public class InventoryModel {

    String purl,pname,ptype,pdescription,quantity;

    InventoryModel(){}

    public InventoryModel(String purl, String pname, String ptype, String pdescription, String quantity) {
        this.purl = purl;
        this.pname = pname;
        this.ptype = ptype;
        this.pdescription = pdescription;
        this.quantity = quantity;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getPdescription() {
        return pdescription;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

