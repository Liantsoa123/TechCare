package org.example.techcare.model.customers;

import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersDAO {

    // Create
    public void createCustomer(Customers customer) {
        String sql = "INSERT INTO customers (email, name) VALUES (?, ?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setString(1, customer.getEmail());
            statement.setString(2, customer.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read (by ID)
    public Customers getCustomerById(int customerId) {
        String sql = "SELECT * FROM customers WHERE customers_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Customers(
                            resultSet.getInt("customers_id"),
                            resultSet.getString("email"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if no customer is found
    }

    // Read (all)
    public List<Customers> getAllCustomers() {
        String sql = "SELECT customers_id, email, name FROM customers";
        List<Customers> customersList = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                customersList.add(new Customers(
                        resultSet.getInt("customers_id"),
                        resultSet.getString("email"),
                        resultSet.getString("name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customersList;
    }

    // Update
    public void updateCustomer(Customers customer) {
        String sql = "UPDATE customers SET email = ?, name = ? WHERE customers_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setString(1, customer.getEmail());
            statement.setString(2, customer.getName());
            statement.setInt(3, customer.getCustomers_id());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customers WHERE customers_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
