package org.example.techcare.dto;

import org.example.techcare.dao.ComponentDAO;
import org.example.techcare.dao.LaptopDAO;
import org.example.techcare.dao.RepairDAO;
import org.example.techcare.dao.TypeComponentDAO;
import org.example.techcare.model.component.Component;
import org.example.techcare.model.component.TypeComponent;
import org.example.techcare.model.laptop.Laptop;
import org.example.techcare.model.repair.Repair;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoRepair {
    private Repair repair;
    private Laptop laptop;
    private Component newComponent;
    private Component oldComponent;
    private TypeComponent typeComponent;

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Component getNewComponent() {
        return newComponent;
    }

    public void setNewComponent(Component newComponent) {
        this.newComponent = newComponent;
    }

    public Component getOldComponent() {
        return oldComponent;
    }

    public void setOldComponent(Component oldComponent) {
        this.oldComponent = oldComponent;
    }

    public TypeComponent getTypeComponent() {
        return typeComponent;
    }

    public void setTypeComponent(TypeComponent typeComponent) {
        this.typeComponent = typeComponent;
    }

    public InfoRepair(Repair repair, Laptop laptop, Component newComponent, Component oldComponent, TypeComponent typeComponent) {
        this.repair = repair;
        this.laptop = laptop;
        this.newComponent = newComponent;
        this.oldComponent = oldComponent;
        this.typeComponent = typeComponent;
    }

    public InfoRepair() {
    }

    public static List<InfoRepair> getByIdTypeLaptopAndIdTypeComponentAndIsUpgrade(Boolean isUpgrade, int typeLaptopId, int typeComponentId) {
        String sql = "select * from v_repair_info where type_laptop_id = ? and type_component_id = ?";
        List<InfoRepair> infoRepairs = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, typeLaptopId);
            statement.setInt(2, typeComponentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Repair repair = new RepairDAO().getRepairById(resultSet.getInt("repair_id"));
                    Laptop laptop = new LaptopDAO().getLaptopById(resultSet.getInt("laptop_id"));
                    Component newC = new ComponentDAO().getComponentById(resultSet.getInt("new_component_id"));
                    Component oldC = new ComponentDAO().getComponentById(resultSet.getInt("old_component_id"));
                    TypeComponent typeComponent = new TypeComponentDAO().getTypeComponentById(resultSet.getInt("type_component_id"));
                    if (isUpgrade == false) {
                        if (oldC.getCapacity() * resultSet.getInt("old_quantity") >= newC.getCapacity() * resultSet.getInt("new_quantity")) {
                            infoRepairs.add(new InfoRepair(repair, laptop, newC, oldC, typeComponent));
                        }
                    } else {
                        if (oldC.getCapacity() * resultSet.getInt("old_quantity") < newC.getCapacity() * resultSet.getInt("new_quantity")) {
                            infoRepairs.add(new InfoRepair(repair, laptop, newC, oldC, typeComponent));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all InfoRepaire: " + e.getMessage());
        }
        return infoRepairs;
    }

}
