package com.example.newyorkerdk.entities;

public class ContactForm {

    private String name;
    private String email;
    private String phonenumber;
    private String city;
    private String note;
    private String supplier;
    
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getCity() {
        return city;
    }

    public String getNote() {
        return note;
    }

    public String getSupplier() {
        return supplier;
    }

}

