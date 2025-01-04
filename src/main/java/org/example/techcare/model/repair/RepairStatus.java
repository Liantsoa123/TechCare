package org.example.techcare.model.repair;

public class RepairStatus {
    private int repair_status_id;
    private String name;

    public RepairStatus() {
    }

    public RepairStatus(int repair_status_id, String name) {
        this.repair_status_id = repair_status_id;
        this.name = name;
    }

    public int getRepair_status_id() {
        return repair_status_id;
    }

    public void setRepair_status_id(int repair_status_id) {
        this.repair_status_id = repair_status_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
