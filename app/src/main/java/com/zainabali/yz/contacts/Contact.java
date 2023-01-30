package com.zainabali.yz.contacts;

public class Contact {
    private  int id;
    private String name;
     private int phone;



    public Contact( String name, int phone) {
        this.name = name;
        this.phone = phone;

    }
    public Contact(int id, String name, int phone) {
        this.name = name;
        this.phone = phone;
        this.id=id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
