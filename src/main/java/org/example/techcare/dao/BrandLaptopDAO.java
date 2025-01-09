package org.example.techcare.dao;

import org.example.techcare.model.brandlaptop.BrandLaptop;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandLaptopDAO {

    // Create
    public void createBrandLaptop(BrandLaptop brandLaptop) {
        String sql = "INSERT INTO brand_laptop (name) VALUES (?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setString(1, brandLaptop.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating BrandLaptop: " + e.getMessage());
        }
    }

    // Read (by ID)
    public BrandLaptop getBrandLaptopById(int brandLaptopId) {
        String sql = "SELECT brand_laptop_id, name FROM brand_laptop WHERE brand_laptop_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, brandLaptopId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new BrandLaptop(
                            resultSet.getInt("brand_laptop_id"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving BrandLaptop by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<BrandLaptop> getAllBrandLaptops() {
        String sql = "SELECT brand_laptop_id, name FROM brand_laptop";
        List<BrandLaptop> brandLaptops = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                brandLaptops.add(new BrandLaptop(
                        resultSet.getInt("brand_laptop_id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all BrandLaptops: " + e.getMessage());
        }
        return brandLaptops;
    }

    // Update
    public void updateBrandLaptop(BrandLaptop brandLaptop) {
        String sql = "UPDATE brand_laptop SET name = ? WHERE brand_laptop_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setString(1, brandLaptop.getName());
            statement.setInt(2, brandLaptop.getBrandLaptopId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating BrandLaptop: " + e.getMessage());
        }
    }

    // Delete
    public void deleteBrandLaptop(int brandLaptopId) {
        String sql = "DELETE FROM brand_laptop WHERE brand_laptop_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, brandLaptopId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting BrandLaptop: " + e.getMessage());
        }
    }
}
