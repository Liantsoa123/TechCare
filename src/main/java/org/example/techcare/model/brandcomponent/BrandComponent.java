package org.example.techcare.model.brandcomponent;

public class BrandComponent {
    private int brandComponentId;
    private String name;

    public BrandComponent() {
    }

    public BrandComponent(int brandComponentId, String name) {
        this.brandComponentId = brandComponentId;
        this.name = name;
    }

    public int getBrandComponentId() {
        return brandComponentId;
    }

    public void setBrandComponentId(int brandComponentId) {
        this.brandComponentId = brandComponentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
