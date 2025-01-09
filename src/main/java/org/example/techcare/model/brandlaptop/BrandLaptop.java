package org.example.techcare.model.brandlaptop;

public class BrandLaptop {
    private int brandLaptopId;
    private String name;

    public BrandLaptop() {
    }

    public BrandLaptop(int brandLaptopId, String name) {
        this.brandLaptopId = brandLaptopId;
        this.name = name;
    }

    public int getBrandLaptopId() {
        return brandLaptopId;
    }

    public void setBrandLaptopId(int brandLaptopId) {
        this.brandLaptopId = brandLaptopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
