package org.example.techcare.dao;

import org.example.techcare.model.component.Component;
import org.example.techcare.model.repair.Repair;
import org.example.techcare.model.repair.RepairDetails;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairDetailsDAO {
    // Create
    public void createRepairDetails(RepairDetails repairDetails) {
        String sql = "INSERT INTO repair_details (quantity, component_id, repair_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, repairDetails.getQuantity());
            statement.setInt(2, repairDetails.getComponent().getComponent_id()); // Using component_id from the Component object
            statement.setInt(3, repairDetails.getRepair().getRepair_id()); // Using repair_id from the Repair object
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating repair details: " + e.getMessage());
        }
    }

    // Read (by ID)
    public RepairDetails getRepairDetailsById(int repairDetailsId) {
        String sql = "SELECT repaire_details_id, quantity, component_id, repair_id FROM repair_details WHERE repaire_details_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, repairDetailsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetching related Component and Repair data
                    ComponentDAO componentDAO = new ComponentDAO();
                    RepairDAO repairDAO = new RepairDAO();

                    Component component = componentDAO.getComponentById(resultSet.getInt("component_id"));
                    Repair repair = repairDAO.getRepairById(resultSet.getInt("repair_id"));

                    return new RepairDetails(
                            resultSet.getInt("repaire_details_id"),
                            resultSet.getInt("quantity"),
                            component,
                            repair
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving repair details by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<RepairDetails> getAllRepairDetails() {
        String sql = "SELECT repaire_details_id, quantity, component_id, repair_id FROM repair_details";
        List<RepairDetails> repairDetailsList = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Fetching related Component and Repair data
                ComponentDAO componentDAO = new ComponentDAO();
                RepairDAO repairDAO = new RepairDAO();

                Component component = componentDAO.getComponentById(resultSet.getInt("component_id"));
                Repair repair = repairDAO.getRepairById(resultSet.getInt("repair_id"));

                repairDetailsList.add(new RepairDetails(
                        resultSet.getInt("repaire_details_id"),
                        resultSet.getInt("quantity"),
                        component,
                        repair
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all repair details: " + e.getMessage());
        }
        return repairDetailsList;
    }

    // Update
    public void updateRepairDetails(RepairDetails repairDetails) {
        String sql = "UPDATE repair_details SET quantity = ?, component_id = ?, repair_id = ? WHERE repaire_details_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, repairDetails.getQuantity());
            statement.setInt(2, repairDetails.getComponent().getComponent_id()); // Using component_id from the Component object
            statement.setInt(3, repairDetails.getRepair().getRepair_id()); // Using repair_id from the Repair object
            statement.setInt(4, repairDetails.getRepaire_details_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating repair details: " + e.getMessage());
        }
    }

    // Delete
    public void deleteRepairDetails(int repairDetailsId) {
        String sql = "DELETE FROM repair_details WHERE repaire_details_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, repairDetailsId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting repair details: " + e.getMessage());
        }
    }
}
