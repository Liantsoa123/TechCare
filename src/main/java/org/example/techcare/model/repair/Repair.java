package org.example.techcare.model.repair;

import org.example.techcare.model.laptop.Laptop;
import org.example.techcare.model.technician.Technician;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Repair {
    private int repair_id;
    private Timestamp filing_date;
    private Timestamp end_date;
    private BigDecimal total;
    private Laptop laptop;
    private Technician technician;
    private RepairStatus repairStatus;

    public Repair() {
    }

    public Repair(int repair_id, Timestamp filing_date, Timestamp end_date, BigDecimal total, Laptop laptop, Technician technician, RepairStatus repairStatus) {
        this.repair_id = repair_id;
        this.filing_date = filing_date;
        this.end_date = end_date;
        this.total = total;
        this.laptop = laptop;
        this.technician = technician;
        this.repairStatus = repairStatus;
    }

    public int getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(int repair_id) {
        this.repair_id = repair_id;
    }

    public Timestamp getFiling_date() {
        return filing_date;
    }

    public void setFiling_date(Timestamp filing_date) {
        this.filing_date = filing_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public RepairStatus getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(RepairStatus repairStatus) {
        this.repairStatus = repairStatus;
    }
}
