package org.example.techcare.model.laptop;

import org.example.techcare.model.brandlaptop.BrandLaptop;
import org.example.techcare.model.customers.Customers;

public class Laptop {
    private int laptop_id;
    private String model;
    private String serial_number;
    private Customers customer;


    private BrandLaptop brandLaptop;

    public Laptop() {
    }

    public Laptop(int laptop_id, String model, String serial_number, Customers customer , BrandLaptop brandLaptop) {
        this.laptop_id = laptop_id;
        this.model = model;
        this.serial_number = serial_number;
        this.customer = customer;
        this.brandLaptop = brandLaptop;
    }

    public int getLaptop_id() {
        return laptop_id;
    }

    public void setLaptop_id(int laptop_id) {
        this.laptop_id = laptop_id;
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

    public BrandLaptop getBrandLaptop() {
        return brandLaptop;
    }

    public void setBrandLaptop(BrandLaptop brandLaptop) {
        this.brandLaptop = brandLaptop;
    }
}
