package org.example.techcare.dao;

import org.example.techcare.model.brandcomponent.BrandComponent;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandComponentDAO {

    // Create
    public void createBrandComponent(BrandComponent brandComponent) {
        String sql = "INSERT INTO brand_component (name) VALUES (?)";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, brandComponent.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating BrandComponent: " + e.getMessage());
        }
    }

    // Read (by ID)
    public BrandComponent getBrandComponentById(int brandComponentId) {
        String sql = "SELECT brand_component_id, name FROM brand_component WHERE brand_component_id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, brandComponentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new BrandComponent(
                            resultSet.getInt("brand_component_id"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving BrandComponent by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<BrandComponent> getAllBrandComponents() {
        String sql = "SELECT brand_component_id, name FROM brand_component";
        List<BrandComponent> brandComponents = new ArrayList<>();
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                brandComponents.add(new BrandComponent(
                        resultSet.getInt("brand_component_id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all BrandComponents: " + e.getMessage());
        }
        return brandComponents;
    }

    // Update
    public void updateBrandComponent(BrandComponent brandComponent) {
        String sql = "UPDATE brand_component SET name = ? WHERE brand_component_id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, brandComponent.getName());
            statement.setInt(2, brandComponent.getBrandComponentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating BrandComponent: " + e.getMessage());
        }
    }

    // Delete
    public void deleteBrandComponent(int brandComponentId) {
        String sql = "DELETE FROM brand_component WHERE brand_component_id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, brandComponentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting BrandComponent: " + e.getMessage());
        }
    }
}
