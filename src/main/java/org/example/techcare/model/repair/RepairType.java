package org.example.techcare.model.repair;

public class RepairType {
    private  int repair_type_id ;
    private  String name ;

    public RepairType() {
    }

    public RepairType(int repair_type_id, String name) {
        this.repair_type_id = repair_type_id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepair_type_id() {
        return repair_type_id;
    }

    public void setRepair_type_id(int repair_type_id) {
        this.repair_type_id = repair_type_id;
    }
}
