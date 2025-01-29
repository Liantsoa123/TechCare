package org.example.techcare.dao;

import org.example.techcare.model.brandcomponent.BrandComponent;
import org.example.techcare.model.component.Component;
import org.example.techcare.model.component.TypeComponent;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComponentDAO {
    // Create
    public void createComponent(Component component) {
        String sql = "INSERT INTO component (unite_price, capacity, type_component_id, brand_component_id, model) VALUES ( ?, ?, ?,?,?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setBigDecimal(1, component.getUnite_price());
            statement.setBigDecimal(2, component.getCapacity());
            statement.setInt(3, component.getTypeComponent().getType_component_id()); // Using the type component ID from the TypeComponent object
            statement.setInt(4, component.getBrandComponent().getBrandComponentId());
            statement.setString(5,component.getModel());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating component: " + e.getMessage());
        }
    }

    // Read (by ID)
    public Component getComponentById(int componentId) {
        String sql = "SELECT * FROM component WHERE component_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, componentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetching the type component data from the type_component table
                    TypeComponentDAO typeComponentDAO = new TypeComponentDAO();
                    TypeComponent typeComponent = typeComponentDAO.getTypeComponentById(resultSet.getInt("type_component_id"));

                    // Fetching the brand component data from the brand_component table
                    BrandComponentDAO brandComponentDAO = new BrandComponentDAO();
                    BrandComponent brandComponent = brandComponentDAO.getBrandComponentById(resultSet.getInt("brand_component_id"));

                    return new Component(
                            resultSet.getInt("component_id"),
                            resultSet.getBigDecimal("unite_price"),
                            resultSet.getBigDecimal("capacity"),
                            typeComponent,
                            brandComponent,
                            resultSet.getString("model")
                            );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving component by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<Component> getAllComponents() {
        String sql = "SELECT * FROM component";
        List<Component> componentList = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Fetching the type component data from the type_component table
                TypeComponentDAO typeComponentDAO = new TypeComponentDAO();
                TypeComponent typeComponent = typeComponentDAO.getTypeComponentById(resultSet.getInt("type_component_id"));

                // Fetching the brand component data from the brand_component table
                BrandComponentDAO brandComponentDAO = new BrandComponentDAO();
                BrandComponent brandComponent = brandComponentDAO.getBrandComponentById(resultSet.getInt("brand_component_id"));


                componentList.add(new Component(
                        resultSet.getInt("component_id"),
                        resultSet.getBigDecimal("unite_price"),
                        resultSet.getBigDecimal("capacity"),
                        typeComponent,
                        brandComponent,
                        resultSet.getString("model")

                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all components: " + e.getMessage());
        }
        return componentList;
    }

    // Update
    public void updateComponent(Component component) {
        String sql = "UPDATE component SET  unite_price = ?, capacity = ?, type_component_id = ? , brand_component_id = ?, model = ? WHERE component_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setBigDecimal(1, component.getUnite_price());
            statement.setBigDecimal(2, component.getCapacity());
            statement.setInt(3, component.getTypeComponent().getType_component_id()); // Using the type component ID from the TypeComponent object
            statement.setInt(6, component.getComponent_id());
            statement.setInt(4,component.getBrandComponent().getBrandComponentId() );
            statement.setString(5, component.getModel());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating component: " + e.getMessage());
        }
    }

    // Delete
    public void deleteComponent(int componentId) {
        String sql = "DELETE FROM component WHERE component_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, componentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting component: " + e.getMessage());
        }
    }
}
