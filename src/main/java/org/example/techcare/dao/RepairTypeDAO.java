package org.example.techcare.dao;

import org.example.techcare.model.repair.RepairType;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairTypeDAO {

    // Read (by ID)
    public RepairType getRepairTypeById(int repairTypeId) {
        String sql = "SELECT repair_type_id, name FROM repair_type WHERE repair_type_id = ?";
        try (Connection conn = new ConnectionBdd().getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, repairTypeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new RepairType(
                            resultSet.getInt("repair_type_id"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving repair type by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<RepairType> getAllRepairTypes() {
        String sql = "SELECT repair_type_id, name FROM repair_type";
        List<RepairType> repairTypeList = new ArrayList<>();
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                repairTypeList.add(new RepairType(
                        resultSet.getInt("repair_type_id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all repair types: " + e.getMessage());
        }
        return repairTypeList;
    }

}