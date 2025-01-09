package org.example.techcare.dao;

import org.example.techcare.model.laptop.Laptop;
import org.example.techcare.model.repair.Repair;
import org.example.techcare.model.repair.RepairStatus;
import org.example.techcare.model.technician.Technician;
import org.example.techcare.model.technician.TechnicianDAO;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairDAO {
    // Create
    public void createRepair(Repair repair) {
        String sql = "INSERT INTO repair (filing_date, end_date, laptop_id, technician_id, repair_status_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setTimestamp(1, repair.getFiling_date());
            statement.setTimestamp(2, repair.getEnd_date());
            statement.setInt(3, repair.getLaptop().getLaptop_id()); // Using laptop_id from the Laptop object
            statement.setInt(4, repair.getTechnician().getTechnician_id()); // Using technician_id from the Technician object
            statement.setInt(5, repair.getRepairStatus().getRepair_status_id()); // Using repair_status_id from the RepairStatus object
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating repair: " + e.getMessage());
        }
    }

    // Read (by ID)
    public Repair getRepairById(int repairId) {
        String sql = "SELECT repair_id, filing_date, end_date, total, laptop_id, technician_id, repair_status_id FROM repair WHERE repair_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, repairId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetching related Laptop, Technician, and RepairStatus data
                    LaptopDAO laptopDAO = new LaptopDAO();
                    TechnicianDAO technicianDAO = new TechnicianDAO();
                    RepairStatusDAO repairStatusDAO = new RepairStatusDAO();

                    Laptop laptop = laptopDAO.getLaptopById(resultSet.getInt("laptop_id"));
                    Technician technician = technicianDAO.getTechnicianById(resultSet.getInt("technician_id"));
                    RepairStatus repairStatus = repairStatusDAO.getRepairStatusById(resultSet.getInt("repair_status_id"));

                    return new Repair(
                            resultSet.getInt("repair_id"),
                            resultSet.getTimestamp("filing_date"),
                            resultSet.getTimestamp("end_date"),
                            laptop,
                            technician,
                            repairStatus
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving repair by ID: " + e.getMessage());
        }
        return null;
    }

    // Read (all)
    public List<Repair> getAllRepairs() {
        String sql = "SELECT repair_id, filing_date, end_date, total, laptop_id, technician_id, repair_status_id FROM repair";
        List<Repair> repairList = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Fetching related Laptop, Technician, and RepairStatus data
                LaptopDAO laptopDAO = new LaptopDAO();
                TechnicianDAO technicianDAO = new TechnicianDAO();
                RepairStatusDAO repairStatusDAO = new RepairStatusDAO();

                Laptop laptop = laptopDAO.getLaptopById(resultSet.getInt("laptop_id"));
                Technician technician = technicianDAO.getTechnicianById(resultSet.getInt("technician_id"));
                RepairStatus repairStatus = repairStatusDAO.getRepairStatusById(resultSet.getInt("repair_status_id"));

                repairList.add(new Repair(
                        resultSet.getInt("repair_id"),
                        resultSet.getTimestamp("filing_date"),
                        resultSet.getTimestamp("end_date"),
                        laptop,
                        technician,
                        repairStatus
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all repairs: " + e.getMessage());
        }
        return repairList;
    }

    // Update
    public void updateRepair(Repair repair) {
        String sql = "UPDATE repair SET filing_date = ?, end_date = ?, laptop_id = ?, technician_id = ?, repair_status_id = ? WHERE repair_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setTimestamp(1, repair.getFiling_date());
            statement.setTimestamp(2, repair.getEnd_date());
            statement.setInt(3, repair.getLaptop().getLaptop_id()); // Using laptop_id from the Laptop object
            statement.setInt(4, repair.getTechnician().getTechnician_id()); // Using technician_id from the Technician object
            statement.setInt(5, repair.getRepairStatus().getRepair_status_id()); // Using repair_status_id from the RepairStatus object
            statement.setInt(6, repair.getRepair_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating repair: " + e.getMessage());
        }
    }

    // Delete
    public void deleteRepair(int repairId) {
        String sql = "DELETE FROM repair WHERE repair_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, repairId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting repair: " + e.getMessage());
        }
    }

    // GET REPAIRS BY TYPE COMPONENT ID
    public List<Repair> getRepairsByTypeComponentId(int idTypeComponent) {
        String sql = """
            SELECT DISTINCT r.repair_id, r.filing_date, r.end_date, r.laptop_id, r.technician_id, r.repair_status_id
            FROM repair r
            INNER JOIN repair_details rd ON r.repair_id = rd.repair_id
            INNER JOIN component c ON rd.componenr_id = c.componenr_id
            WHERE c.type_component_id = ?
        """;

        List<Repair> repairs = new ArrayList<>();

        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, idTypeComponent);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Repair repair = new Repair(
                            resultSet.getInt("repair_id"),
                            resultSet.getTimestamp("filing_date"),
                            resultSet.getTimestamp("end_date"),
                            new LaptopDAO().getLaptopById(resultSet.getInt("laptop_id")),
                            new TechnicianDAO().getTechnicianById(resultSet.getInt("technician_id")),
                            new RepairStatusDAO().getRepairStatusById(resultSet.getInt("repair_status_id"))
                    );
                    repairs.add(repair);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching repairs by TypeComponentId: " + e.getMessage());
        }

        return repairs;
    }
}

