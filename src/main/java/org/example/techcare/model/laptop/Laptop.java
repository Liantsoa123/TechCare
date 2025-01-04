package org.example.techcare.model.laptop;

import org.example.techcare.model.customers.Customers;

public class Laptop {
    private int laptop_id;
    private String brand;
    private String model;
    private String serial_number;
    private Customers customer;

    public Laptop() {
    }

    public Laptop(int laptop_id, String brand, String model, String serial_number, Customers customer) {
        this.laptop_id = laptop_id;
        this.brand = brand;
        this.model = model;
        this.serial_number = serial_number;
        this.customer = customer;
    }

    public int getLaptop_id() {
        return laptop_id;
    }

    public void setLaptop_id(int laptop_id) {
        this.laptop_id = laptop_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
}
