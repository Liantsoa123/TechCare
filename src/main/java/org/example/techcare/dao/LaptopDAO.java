package org.example.techcare.dao;

import org.example.techcare.model.brandlaptop.BrandLaptop;
import org.example.techcare.model.customers.Customers;
import org.example.techcare.model.laptop.Laptop;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaptopDAO {
    // Create
    public void createLaptop(Laptop laptop) {
        String sql = "INSERT INTO laptop ( model, serial_number, customers_id , brand_laptop_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {

            statement.setString(1, laptop.getModel());
            statement.setString(2, laptop.getSerial_number());
            statement.setInt(3, laptop.getCustomer().getCustomers_id());
            statement.setInt(4, laptop.getBrandLaptop().getBrandLaptopId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating laptop: " + e.getMessage());
        }
    }

    // Read (by ID)
    public Laptop getLaptopById(int laptopId) {
        String sql = "SELECT laptop_id, brand_laptop_id, model, serial_number, customers_id FROM laptop WHERE laptop_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, laptopId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetching the customer data from the customers table
                    CustomersDAO customersDAO = new CustomersDAO();
                    Customers customer = customersDAO.getCustomerById(resultSet.getInt("customers_id"));

                    // Fetching the brand laptop data from the brand_laptop table
                    BrandLaptopDAO brandLaptopDAO = new BrandLaptopDAO();
                    BrandLaptop brandLaptop = brandLaptopDAO.getBrandLaptopById(resultSet.getInt("brand_laptop_id"));

                    return new Laptop(
                            resultSet.getInt("laptop_id"),
                            resultSet.getString("model"),
                            resultSet.getString("serial_number"),
                            customer,
                            brandLaptop
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving laptop by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<Laptop> getAllLaptops() {
        String sql = "SELECT laptop_id, brand_laptop_id, model, serial_number, customers_id FROM laptop";
        List<Laptop> laptopList = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Fetching the customer data from the customers table
                CustomersDAO customersDAO = new CustomersDAO();
                Customers customer = customersDAO.getCustomerById(resultSet.getInt("customers_id"));

                // Fetching the brand laptop data from the brand_laptop table
                BrandLaptopDAO brandLaptopDAO = new BrandLaptopDAO();
                BrandLaptop brandLaptop = brandLaptopDAO.getBrandLaptopById(resultSet.getInt("brand_laptop_id"));

                laptopList.add(new Laptop(
                        resultSet.getInt("laptop_id"),
                        resultSet.getString("model"),
                        resultSet.getString("serial_number"),
                        customer,
                        brandLaptop
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all laptops: " + e.getMessage());
        }
        return laptopList;
    }

    // Update
    public void updateLaptop(Laptop laptop) {
        String sql = "UPDATE laptop SET brand_laptop_id = ?, model = ?, serial_number = ?, customers_id = ? WHERE laptop_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, laptop.getBrandLaptop().getBrandLaptopId());
            statement.setString(2, laptop.getModel());
            statement.setString(3, laptop.getSerial_number());
            statement.setInt(4, laptop.getCustomer().getCustomers_id()); // Using the customer ID from the Customers object
            statement.setInt(5, laptop.getLaptop_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating laptop: " + e.getMessage());
        }
    }

    // Delete
    public void deleteLaptop(int laptopId) {
        String sql = "DELETE FROM laptop WHERE laptop_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, laptopId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting laptop: " + e.getMessage());
        }
    }
}
