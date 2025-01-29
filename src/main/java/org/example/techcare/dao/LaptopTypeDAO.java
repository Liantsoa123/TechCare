package org.example.techcare.dao;

import org.example.techcare.model.laptotype.LaptopType;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaptopTypeDAO {

    // Create
    public void createLaptopType(LaptopType laptopType) {
        String sql = "INSERT INTO laptop_type (name) VALUES (?)";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, laptopType.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating LaptopType: " + e.getMessage());
        }
    }

    // Read (by ID)
    public LaptopType getLaptopTypeById(int laptopTypeId) {
        String sql = "SELECT laptop_type_id, name FROM laptop_type WHERE laptop_type_id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, laptopTypeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new LaptopType(
                            resultSet.getInt("laptop_type_id"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving LaptopType by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<LaptopType> getAllLaptopTypes() {
        String sql = "SELECT laptop_type_id, name FROM laptop_type";
        List<LaptopType> laptopTypes = new ArrayList<>();
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                laptopTypes.add(new LaptopType(
                        resultSet.getInt("laptop_type_id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all LaptopTypes: " + e.getMessage());
        }
        return laptopTypes;
    }

    // Update
    public void updateLaptopType(LaptopType laptopType) {
        String sql = "UPDATE laptop_type SET name = ? WHERE laptop_type_id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, laptopType.getName());
            statement.setInt(2, laptopType.getLaptopTypeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating LaptopType: " + e.getMessage());
        }
    }

    // Delete
    public void deleteLaptopType(int laptopTypeId) {
        String sql = "DELETE FROM laptop_type WHERE laptop_type_id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, laptopTypeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting LaptopType: " + e.getMessage());
        }
    }
}
