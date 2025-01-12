package org.example.techcare.dao;


import org.example.techcare.model.laptop.Laptop;
import org.example.techcare.model.component.Component;
import org.example.techcare.model.laptopcomponent.LaptopComponent;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaptopComponentDAO {

    // Create
    public void createLaptopComponent(LaptopComponent laptopComponent) {
        String sql = "INSERT INTO laptop_component (laptop_id, component_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, laptopComponent.getLaptop().getLaptop_id());
            statement.setInt(2, laptopComponent.getComponent().getComponent_id());
            statement.setInt(3, laptopComponent.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating LaptopComponent: " + e.getMessage());
        }
    }

    //Read (by id_laptop)
    public LaptopComponent getLaptopComponentByLaptopId(int laptopId) {
        String sql = "SELECT laptop_id, component_id, quantity FROM laptop_component WHERE laptop_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, laptopId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LaptopDAO laptopDAO = new LaptopDAO();
                    ComponentDAO componentDAO = new ComponentDAO();

                    Laptop laptop = laptopDAO.getLaptopById(resultSet.getInt("laptop_id"));
                    Component component = componentDAO.getComponentById(resultSet.getInt("component_id"));

                    return new LaptopComponent(
                            laptop,
                            component,
                            resultSet.getInt("quantity")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving LaptopComponent by IDs: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<LaptopComponent> getAllLaptopComponents() {
        String sql = "SELECT laptop_id, component_id, quantity FROM laptop_component";
        List<LaptopComponent> laptopComponents = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            LaptopDAO laptopDAO = new LaptopDAO();
            ComponentDAO componentDAO = new ComponentDAO();

            while (resultSet.next()) {
                Laptop laptop = laptopDAO.getLaptopById(resultSet.getInt("laptop_id"));
                Component component = componentDAO.getComponentById(resultSet.getInt("component_id"));

                laptopComponents.add(new LaptopComponent(
                        laptop,
                        component,
                        resultSet.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all LaptopComponents: " + e.getMessage());
        }
        return laptopComponents;
    }

    // Update
    public void updateLaptopComponent(LaptopComponent laptopComponent) {
        String sql = "UPDATE laptop_component SET quantity = ? WHERE laptop_id = ? AND component_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, laptopComponent.getQuantity());
            statement.setInt(2, laptopComponent.getLaptop().getLaptop_id());
            statement.setInt(3, laptopComponent.getComponent().getComponent_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating LaptopComponent: " + e.getMessage());
        }
    }

    // Delete
    public void deleteLaptopComponent(int laptopId, int componentId) {
        String sql = "DELETE FROM laptop_component WHERE laptop_id = ? AND component_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, laptopId);
            statement.setInt(2, componentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting LaptopComponent: " + e.getMessage());
        }
    }
}

