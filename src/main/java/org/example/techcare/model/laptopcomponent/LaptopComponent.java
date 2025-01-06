package org.example.techcare.model.laptopcomponent;

import org.example.techcare.model.laptop.Laptop;
import org.example.techcare.model.component.Component;

public class LaptopComponent {
    private Laptop laptop;
    private Component component;
    private int quantity;

    public LaptopComponent() {
    }

    public LaptopComponent(Laptop laptop, Component component, int quantity) {
        this.laptop = laptop;
        this.component = component;
        this.quantity = quantity;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
