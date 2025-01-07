package org.example.techcare.model.component;

import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeComponentDAO {
    // Read (by ID)
    public TypeComponent getTypeComponentById(int typeComponentId) {
        String sql = "SELECT * FROM type_component WHERE type_component_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, typeComponentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new TypeComponent(
                            resultSet.getInt("type_component_id"),
                            resultSet.getString("name"),
                            resultSet.getString("unit")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving type component by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<TypeComponent> getAllTypeComponents() {
        String sql = "SELECT * FROM type_component";
        List<TypeComponent> typeComponentList = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                typeComponentList.add(new TypeComponent(
                        resultSet.getInt("type_component_id"),
                        resultSet.getString("name"),
                        resultSet.getString("unit")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all type components: " + e.getMessage());
            e.printStackTrace();
        }
        return typeComponentList;
    }
}
