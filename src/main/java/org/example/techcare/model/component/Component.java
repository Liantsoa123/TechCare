package org.example.techcare.model.component;


import org.example.techcare.model.brandcomponent.BrandComponent;

import java.math.BigDecimal;

public class Component {
    private int component_id;
    private BigDecimal unite_price;
    private int capacity;
    private TypeComponent typeComponent;
    private BrandComponent brandComponent;

    public Component() {
    }

    public Component(int componenr_id, BigDecimal unite_price, int capacity, TypeComponent typeComponent , BrandComponent brandComponent) {
        this.component_id = componenr_id;
        this.unite_price = unite_price;
        this.capacity = capacity;
        this.typeComponent = typeComponent;
        this.brandComponent = brandComponent;
    }

    public int getComponent_id() {
        return component_id;
    }

    public void setComponent_id(int componenr_id) {
        this.component_id = componenr_id;
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
    public BrandComponent getBrandComponent() {
        return brandComponent;
    }

    public void setBrandComponent(BrandComponent brandComponent) {
        this.brandComponent = brandComponent;
    }
}
