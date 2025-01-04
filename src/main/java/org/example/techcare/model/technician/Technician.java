package org.example.techcare.model.technician;

public class Technician {
    private int technician_id;
    private String name;
    private String email;

    public Technician() {
    }

    public Technician(int technician_id, String name, String email) {
        this.technician_id = technician_id;
        this.name = name;
        this.email = email;
    }

    public int getTechnician_id() {
        return technician_id;
    }

    public void setTechnician_id(int technician_id) {
        this.technician_id = technician_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
