package com.example.ad37_nguyenngocdung_day7;

public class Contact {
    String name;
    String phone;
    String country;

    public Contact(String phone, String country, String name) {
        this.phone = phone;
        this.country = country;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
