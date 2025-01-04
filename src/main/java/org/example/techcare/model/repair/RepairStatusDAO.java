package org.example.techcare.model.repair;

import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairStatusDAO {

    // Read (by ID)
    public RepairStatus getRepairStatusById(int repairStatusId) {
        String sql = "SELECT repair_status_id, name FROM repair_status WHERE repair_status_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
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
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
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

