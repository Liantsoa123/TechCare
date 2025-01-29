package org.example.techcare.dao;

import org.example.techcare.model.repair.RepairStatus;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairStatusDAO {

    // Read (by ID)
    public RepairStatus getRepairStatusById(int repairStatusId) {
        String sql = "SELECT repair_status_id, name FROM repair_status WHERE repair_status_id = ?";
        try (Connection conn = new ConnectionBdd().getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, repairStatusId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new RepairStatus(
                            resultSet.getInt("repair_status_id"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving repair status by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<RepairStatus> getAllRepairStatuses() {
        String sql = "SELECT repair_status_id, name FROM repair_status";
        List<RepairStatus> repairStatusList = new ArrayList<>();
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                repairStatusList.add(new RepairStatus(
                        resultSet.getInt("repair_status_id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all repair statuses: " + e.getMessage());
        }
        return repairStatusList;
    }

}

