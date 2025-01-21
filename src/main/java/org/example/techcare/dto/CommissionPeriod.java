package org.example.techcare.dto;

import org.example.techcare.model.technician.Technician;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class CommissionPeriod {
    private Technician technician;
    private BigDecimal total;

    public CommissionPeriod() {
    }

    public CommissionPeriod(Technician technician, BigDecimal total) {
        this.technician = technician;
        this.total = total;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public static List<CommissionPeriod> getRepair(Technician technician, Date dateDebut, Date dateFin){

    }
}
