package org.example.techcare.model.laptotype;

public class LaptopType {
    private int laptopTypeId;
    private String name;

    public int getLaptopTypeId() {
        return laptopTypeId;
    }

    public void setLaptopTypeId(int laptopTypeId) {
        this.laptopTypeId = laptopTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LaptopType(int laptopTypeId, String name) {
        this.laptopTypeId = laptopTypeId;
        this.name = name;
    }

    public LaptopType() {
    }
}
