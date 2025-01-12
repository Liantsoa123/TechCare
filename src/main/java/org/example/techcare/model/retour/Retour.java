package org.example.techcare.model.retour;

import org.example.techcare.model.repair.Repair;

import java.sql.Timestamp;

public class Retour {
    private int retour_id;
    private Timestamp retour_date;
    private Repair repair ;

    public Retour() {
    }

    public Retour(int retour_id, Timestamp retour_date, Repair repair) {
        this.retour_id = retour_id;
        this.retour_date = retour_date;
        this.repair = repair;
    }

    public int getRetour_id() {
        return retour_id;
    }

    public void setRetour_id(int retour_id) {
        this.retour_id = retour_id;
    }

    public Timestamp getRetour_date() {
        return retour_date;
    }

    public void setRetour_date(Timestamp retour_date) {
        this.retour_date = retour_date;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }
}
