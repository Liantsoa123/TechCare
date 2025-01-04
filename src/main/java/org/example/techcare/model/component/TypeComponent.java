package org.example.techcare.model.component;

public class TypeComponent {
    private int type_component_id;
    private String name;

    public TypeComponent() {
    }

    public TypeComponent(int type_component_id, String name) {
        this.type_component_id = type_component_id;
        this.name = name;
    }

    public int getType_component_id() {
        return type_component_id;
    }

    public void setType_component_id(int type_component_id) {
        this.type_component_id = type_component_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
