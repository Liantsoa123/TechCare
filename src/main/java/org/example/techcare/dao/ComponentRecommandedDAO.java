package org.example.techcare.dao;

import org.example.techcare.model.ComponentRecommanded.ComponentRecommanded;
import org.example.techcare.model.component.Component;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComponentRecommandedDAO {

    // Create
    public void createComponentRecommanded(ComponentRecommanded componentRecommande) {
        String sql = "INSERT INTO composant_recommande (component_id, date_recommande) VALUES (?, ?)";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, componentRecommande.getComponent().getComponent_id());
            statement.setDate(2, componentRecommande.getDate_recommande());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating ComponentRecommande: " + e.getMessage());
        }
    }

    // Read (by ID)
    public ComponentRecommanded getComponentRecommandeById(int id) {
        String sql = "SELECT cr.id, cr.date_recommande, c.id AS component_id, c.name AS component_name " +
                "FROM composant_recommande cr " +
                "JOIN component c ON cr.component_id = c.id " +
                "WHERE cr.id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Component component =new ComponentDAO().getComponentById(resultSet.getInt("component_id"));
                    return new ComponentRecommanded(
                            resultSet.getInt("id"),
                            resultSet.getDate("date_recommande"),
                            component
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving ComponentRecommande by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<ComponentRecommanded> getAllComponentRecommandes() {
        String sql = "SELECT * from composant_recommande ";
        List<ComponentRecommanded> componentRecommandes = new ArrayList<>();
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Component component =new ComponentDAO().getComponentById(resultSet.getInt("component_id"));
                componentRecommandes.add(new ComponentRecommanded(
                        resultSet.getInt("id"),
                        resultSet.getDate("date_recommande"),
                        component
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all ComponentRecommandes: " + e.getMessage());
        }
        return componentRecommandes;
    }

    // Update
    public void updateComponentRecommande(ComponentRecommanded componentRecommande) {
        String sql = "UPDATE composant_recommander SET component_id = ?, date_recommande = ? WHERE id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, componentRecommande.getComponent().getComponent_id());
            statement.setDate(2, componentRecommande.getDate_recommande());
            statement.setInt(3, componentRecommande.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating ComponentRecommande: " + e.getMessage());
        }
    }

    // Delete
    public void deleteComponentRecommande(int id) {
        String sql = "DELETE FROM composant_recommande WHERE id = ?";
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting ComponentRecommande: " + e.getMessage());
        }
    }

    // Get by month
    public List<ComponentRecommanded> getByMonth(Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(java.util.Calendar.YEAR);
        int month = cal.get(java.util.Calendar.MONTH) + 1;

        String sql = "SELECT * " +
                "FROM composant_recommande cr " +
                "JOIN component c ON cr.component_id = c.component_id " +
                "WHERE EXTRACT(YEAR FROM cr.date_recommande) = ? AND EXTRACT(MONTH FROM cr.date_recommande) = ?";
        List<ComponentRecommanded> componentRecommandes = new ArrayList<>();
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, year);
            statement.setInt(2, month);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Component component = new ComponentDAO().getComponentById(resultSet.getInt("component_id"));
                    componentRecommandes.add(new ComponentRecommanded(
                            resultSet.getInt("id"),
                            resultSet.getDate("date_recommande"),
                            component
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving ComponentRecommandes by month: " + e.getMessage());
        }
        return componentRecommandes;
    }

    // Get by year
    public List<ComponentRecommanded> getByYear(Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(java.util.Calendar.YEAR);
        int month = cal.get(java.util.Calendar.MONTH) + 1;

        String sql = "SELECT * " +
                "FROM composant_recommande cr " +
                "JOIN component c ON cr.component_id = c.component_id " +
                "WHERE EXTRACT(YEAR FROM cr.date_recommande) = ? ";
        List<ComponentRecommanded> componentRecommandes = new ArrayList<>();
        try (Connection conn = new ConnectionBdd().getConnection();PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, year);
            statement.setInt(2, month);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Component component = new ComponentDAO().getComponentById(resultSet.getInt("component_id"));
                    componentRecommandes.add(new ComponentRecommanded(
                            resultSet.getInt("id"),
                            resultSet.getDate("date_recommande"),
                            component
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving ComponentRecommandes by month: " + e.getMessage());
        }
        return componentRecommandes;
    }
}
