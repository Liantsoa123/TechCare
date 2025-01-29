package org.example.techcare.dao;

import org.example.techcare.model.component.Component;
import org.example.techcare.model.histo.HistoriquePrix;
import org.example.techcare.model.utils.ConnectionBdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoriquePrixDAO {

    // Create
    public void createHistoriquePrix(HistoriquePrix historiquePrix) {
        String sql = "INSERT INTO historique_prix (dateHisto, prix, component_id) VALUES (?, ?, ?)";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(historiquePrix.getDateHisto()));
            statement.setBigDecimal(2, historiquePrix.getPrix());
            statement.setInt(3, historiquePrix.getComponent().getComponent_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating historique prix: " + e.getMessage());
        }
    }

    // Read (by ID)
    public HistoriquePrix getHistoriquePrixById(int id) {
        String sql = "SELECT * FROM historique_prix WHERE id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ComponentDAO componentDAO = new ComponentDAO();
                    Component component = componentDAO.getComponentById(resultSet.getInt("component_id"));

                    return new HistoriquePrix(
                            resultSet.getInt("id"),
                            resultSet.getDate("dateHisto").toLocalDate(),
                            resultSet.getBigDecimal("prix"),
                            component
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving historique prix by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<HistoriquePrix> getAllHistoriquePrix() {
        String sql = "SELECT * FROM historique_prix";
        List<HistoriquePrix> historiquePrixList = new ArrayList<>();
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            ComponentDAO componentDAO = new ComponentDAO();

            while (resultSet.next()) {
                Component component = componentDAO.getComponentById(resultSet.getInt("component_id"));

                historiquePrixList.add(new HistoriquePrix(
                        resultSet.getInt("id"),
                        resultSet.getDate("dateHisto").toLocalDate(),
                        resultSet.getBigDecimal("prix"),
                        component
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all historique prix: " + e.getMessage());
        }
        return historiquePrixList;
    }

    // Update
    public void updateHistoriquePrix(HistoriquePrix historiquePrix) {
        String sql = "UPDATE historique_prix SET dateHisto = ?, prix = ?, component_id = ? WHERE id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(historiquePrix.getDateHisto()));
            statement.setBigDecimal(2, historiquePrix.getPrix());
            statement.setInt(3, historiquePrix.getComponent().getComponent_id());
            statement.setInt(4, historiquePrix.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating historique prix: " + e.getMessage());
        }
    }

    // Delete
    public void deleteHistoriquePrix(int id) {
        String sql = "DELETE FROM historique_prix WHERE id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting historique prix: " + e.getMessage());
        }
    }

    // Read (by Component ID)
    public List<HistoriquePrix> getHistoriqueByIdComponent(int componentId) {
        String sql = "SELECT * FROM historique_prix WHERE component_id = ?";
        List<HistoriquePrix> historiquePrixList = new ArrayList<>();
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, componentId);
            try (ResultSet resultSet = statement.executeQuery()) {

                ComponentDAO componentDAO = new ComponentDAO();
                Component component = componentDAO.getComponentById(componentId); // Fetch the associated Component

                while (resultSet.next()) {
                    historiquePrixList.add(new HistoriquePrix(
                            resultSet.getInt("id"),
                            resultSet.getDate("dateHisto").toLocalDate(),
                            resultSet.getBigDecimal("prix"),
                            component
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving historique prix by Component ID: " + e.getMessage());
        }
        return historiquePrixList;
    }

    public  BigDecimal getLastPrice(int componentId) {
        String sql = "SELECT prix FROM historique_prix WHERE component_id = ? ORDER BY dateHisto DESC LIMIT 1";
        try (Connection conn = new ConnectionBdd().getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, componentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBigDecimal("prix");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving last price by Component ID: " + e.getMessage());
        }
        return null;
    }
}
