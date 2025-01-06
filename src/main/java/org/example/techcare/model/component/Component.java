package org.example.techcare.model.component;


import java.math.BigDecimal;

public class Component {
    private int componenr_id;
    private String brand;
    private BigDecimal unite_price;
    private int capacity;
    private TypeComponent typeComponent;

    public Component() {
    }

    public Component(int componenr_id, String brand, BigDecimal unite_price, int capacity, TypeComponent typeComponent) {
        this.componenr_id = componenr_id;
        this.brand = brand;
        this.unite_price = unite_price;
        this.capacity = capacity;
        this.typeComponent = typeComponent;
    }

    public int getComponenr_id() {
        return componenr_id;
    }

    public void setComponenr_id(int componenr_id) {
        this.componenr_id = componenr_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getUnite_price() {
        return unite_price;
    }

    public void setUnite_price(BigDecimal unite_price) {
        this.unite_price = unite_price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TypeComponent getTypeComponent() {
        return typeComponent;
    }

    public void setTypeComponent(TypeComponent typeComponent) {
        this.typeComponent = typeComponent;
    }
}
