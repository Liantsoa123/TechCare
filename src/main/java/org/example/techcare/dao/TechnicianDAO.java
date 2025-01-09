package org.example.techcare.dao;

import org.example.techcare.model.technician.Technician;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TechnicianDAO {
    // Create
    public void createTechnician(Technician technician) {
        String sql = "INSERT INTO technician (name, email) VALUES (?, ?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setString(1, technician.getName());
            statement.setString(2, technician.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating technician: " + e.getMessage());
        }
    }

    // Read (by ID)
    public Technician getTechnicianById(int technicianId) {
        String sql = "SELECT technician_id, name, email FROM technician WHERE technician_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, technicianId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Technician(
                            resultSet.getInt("technician_id"),
                            resultSet.getString("name"),
                            resultSet.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving technician by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<Technician> getAllTechnicians() {
        String sql = "SELECT technician_id, name, email FROM technician";
        List<Technician> technicianList = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                technicianList.add(new Technician(
                        resultSet.getInt("technician_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all technicians: " + e.getMessage());
        }
        return technicianList;
    }

    // Update
    public void updateTechnician(Technician technician) {
        String sql = "UPDATE technician SET name = ?, email = ? WHERE technician_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setString(1, technician.getName());
            statement.setString(2, technician.getEmail());
            statement.setInt(3, technician.getTechnician_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating technician: " + e.getMessage());
        }
    }

    // Delete
    public void deleteTechnician(int technicianId) {
        String sql = "DELETE FROM technician WHERE technician_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, technicianId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting technician: " + e.getMessage());
        }
    }
}
