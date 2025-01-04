package org.example.techcare.model.repair;

import org.example.techcare.model.component.Component;

public class RepairDetails {
    private int repaire_details_id;
    private int quantity;
    private Component component;
    private Repair repair;

    public RepairDetails() {
    }

    public RepairDetails(int repaire_details_id, int quantity, Component component, Repair repair) {
        this.repaire_details_id = repaire_details_id;
        this.quantity = quantity;
        this.component = component;
        this.repair = repair;
    }

    public int getRepaire_details_id() {
        return repaire_details_id;
    }

    public void setRepaire_details_id(int repaire_details_id) {
        this.repaire_details_id = repaire_details_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }
}
