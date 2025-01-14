package org.example.techcare.dao;

import org.example.techcare.model.ComponentRecommande.ComponentRecommande;
import org.example.techcare.model.component.Component;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComponentRecommandeDAO {

    // Create
    public void createComponentRecommande(ComponentRecommande componentRecommande) {
        String sql = "INSERT INTO composant_recommande (component_id, date_recommande) VALUES (?, ?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, componentRecommande.getComponent().getComponent_id());
            statement.setDate(2, componentRecommande.getDate_recommande());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating ComponentRecommande: " + e.getMessage());
        }
    }

    // Read (by ID)
    public ComponentRecommande getComponentRecommandeById(int id) {
        String sql = "SELECT cr.id, cr.date_recommande, c.id AS component_id, c.name AS component_name " +
                "FROM composant_recommande cr " +
                "JOIN component c ON cr.component_id = c.id " +
                "WHERE cr.id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Component component =new ComponentDAO().getComponentById(resultSet.getInt("component_id"));
                    return new ComponentRecommande(
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
    public List<ComponentRecommande> getAllComponentRecommandes() {
        String sql = "SELECT * from composant_recommande ";
        List<ComponentRecommande> componentRecommandes = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Component component =new ComponentDAO().getComponentById(resultSet.getInt("component_id"));
                componentRecommandes.add(new ComponentRecommande(
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
    public void updateComponentRecommande(ComponentRecommande componentRecommande) {
        String sql = "UPDATE composant_recommander SET component_id = ?, date_recommande = ? WHERE id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
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
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting ComponentRecommande: " + e.getMessage());
        }
    }

    public List<ComponentRecommande> getByMonth(Date date) {
        // Extract year and month from the date
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(java.util.Calendar.YEAR);
        int month = cal.get(java.util.Calendar.MONTH) + 1; // MONTH is 0-based, so we add 1

        String sql = "SELECT * " +
                "FROM composant_recommande cr " +
                "JOIN component c ON cr.component_id = c.component_id " +
                "WHERE EXTRACT(YEAR FROM cr.date_recommande) = ? AND EXTRACT(MONTH FROM cr.date_recommande) = ?";
        List<ComponentRecommande> componentRecommandes = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, year);
            statement.setInt(2, month);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Component component = new ComponentDAO().getComponentById(resultSet.getInt("component_id"));
                    componentRecommandes.add(new ComponentRecommande(
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
