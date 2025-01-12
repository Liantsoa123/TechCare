package org.example.techcare.dao;


import org.example.techcare.model.component.Component;
import org.example.techcare.model.stock.StockMovement;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockMovementDAO {

    // Create
    public void createStockMovement(StockMovement stockMovement) {
        String sql = "INSERT INTO stock_movement (quantity, is_enter, date_movement, component_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setBigDecimal(1, stockMovement.getQuantity());
            statement.setBoolean(2, stockMovement.isEnter());
            statement.setTimestamp(3, stockMovement.getDateMovement());
            statement.setInt(4, stockMovement.getComponent().getComponent_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating StockMovement: " + e.getMessage());
        }
    }

    // Read (by ID)
    public StockMovement getStockMovementById(int stockMovementId) {
        String sql = "SELECT stock_movement_id, quantity, is_enter, date_movement, component_id FROM stock_movement WHERE stock_movement_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, stockMovementId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ComponentDAO componentDAO = new ComponentDAO();
                    Component component = componentDAO.getComponentById(resultSet.getInt("component_id"));

                    return new StockMovement(
                            resultSet.getInt("stock_movement_id"),
                            resultSet.getBigDecimal("quantity"),
                            resultSet.getBoolean("is_enter"),
                            resultSet.getTimestamp("date_movement"),
                            component
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving StockMovement by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<StockMovement> getAllStockMovements() {
        String sql = "SELECT stock_movement_id, quantity, is_enter, date_movement, component_id FROM stock_movement";
        List<StockMovement> stockMovements = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            ComponentDAO componentDAO = new ComponentDAO();

            while (resultSet.next()) {
                Component component = componentDAO.getComponentById(resultSet.getInt("component_id"));

                stockMovements.add(new StockMovement(
                        resultSet.getInt("stock_movement_id"),
                        resultSet.getBigDecimal("quantity"),
                        resultSet.getBoolean("is_enter"),
                        resultSet.getTimestamp("date_movement"),
                        component
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all StockMovements: " + e.getMessage());
        }
        return stockMovements;
    }

    // Update
    public void updateStockMovement(StockMovement stockMovement) {
        String sql = "UPDATE stock_movement SET quantity = ?, is_enter = ?, date_movement = ?, component_id = ? WHERE stock_movement_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setBigDecimal(1, stockMovement.getQuantity());
            statement.setBoolean(2, stockMovement.isEnter());
            statement.setTimestamp(3, stockMovement.getDateMovement());
            statement.setInt(4, stockMovement.getComponent().getComponent_id());
            statement.setInt(5, stockMovement.getStockMovementId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating StockMovement: " + e.getMessage());
        }
    }

    // Delete
    public void deleteStockMovement(int stockMovementId) {
        String sql = "DELETE FROM stock_movement WHERE stock_movement_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, stockMovementId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting StockMovement: " + e.getMessage());
        }
    }
}
