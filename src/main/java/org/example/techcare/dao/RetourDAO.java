package org.example.techcare.dao;

import org.example.techcare.dto.InfoRepair;
import org.example.techcare.model.repair.Repair;
import org.example.techcare.model.retour.Retour;
import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetourDAO {
    //Create
    public void createRetour(Retour retour) {
        String sql = "INSERT INTO retour (retour_date, repair_id) VALUES (?, ?)";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setTimestamp(1, retour.getRetour_date());
            statement.setInt(2, retour.getRepair().getRepair_id()); // Using repair_id from the Repair object
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while creating retour: " + e.getMessage());
        }
    }

    //Read (by ID)
    public Retour getRetourById(int retourId) {
        String sql = "SELECT retour_id, retour_date, repair_id FROM retour WHERE retour_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, retourId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetching related Repair data
                    RepairDAO repairDAO = new RepairDAO();
                    Repair repair = repairDAO.getRepairById(resultSet.getInt("repair_id"));

                    return new Retour(
                            resultSet.getInt("retour_id"),
                            resultSet.getTimestamp("retour_date"),
                            repair
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving retour by ID: " + e.getMessage());
        }
        return null;
    }

    //Read by repair_id
    public Retour getRetourByRepairId(int repairId) {
        String sql = "SELECT retour_id, retour_date, repair_id FROM retour WHERE repair_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, repairId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetching related Repair data
                    RepairDAO repairDAO = new RepairDAO();
                    Repair repair = repairDAO.getRepairById(resultSet.getInt("repair_id"));

                    return new Retour(
                            resultSet.getInt("retour_id"),
                            resultSet.getTimestamp("retour_date"),
                            repair
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving retour by repair ID: " + e.getMessage());
        }
        return null;
    }

    //Read (all)
    public List<Retour> getAllRetours() {
        String sql = "SELECT retour_id, retour_date, repair_id FROM retour";
        List<Retour> retourList = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Fetching related Repair data
                RepairDAO repairDAO = new RepairDAO();
                Repair repair = repairDAO.getRepairById(resultSet.getInt("repair_id"));

                retourList.add(new Retour(
                        resultSet.getInt("retour_id"),
                        resultSet.getTimestamp("retour_date"),
                        repair
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all retours: " + e.getMessage());
        }
        return retourList;
    }

    //Update
    public void updateRetour(Retour retour) {
        String sql = "UPDATE retour SET retour_date = ?, repair_id = ? WHERE retour_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setTimestamp(1, retour.getRetour_date());
            statement.setInt(2, retour.getRepair().getRepair_id());
            statement.setInt(3, retour.getRetour_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating retour: " + e.getMessage());
        }
    }

    //Delete
    public void deleteRetour(int retourId) {
        String sql = "DELETE FROM retour WHERE retour_id = ?";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            statement.setInt(1, retourId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting retour: " + e.getMessage());
        }
    }

    //Read by idTypeComponent and IdTypeLaptop
    public List<Retour> getRetourByTypeComponentIdAndTypeLaptopId(int idTypeComponent, int idTypeLaptop, boolean isUpgrade) {
        List<Retour> retourList = new ArrayList<>();
        List<InfoRepair> infoRepairs = InfoRepair.getByIdTypeLaptopAndIdTypeComponentAndIsUpgrade(isUpgrade, idTypeLaptop, idTypeComponent);
        for (InfoRepair infoRepair : infoRepairs) {
            Retour  retour = getRetourByRepairId(infoRepair.getRepair().getRepair_id());
            if (retour!= null) {
                retour.setOldComponent(infoRepair.getOldComponent());
                retour.setNewComponent(infoRepair.getNewComponent());
                retourList.add(retour);
            }
        }
        return retourList;
    }
    public List<Retour> getByDate( Date date) {
        String sql = "SELECT retour_id, retour_date, repair_id FROM retour WHERE DATE(retour_date) = ? ";
        List<Retour> retourList = new ArrayList<>();
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)){
             statement.setDate(1,date);
             ResultSet resultSet = statement.executeQuery() ;

            while (resultSet.next()) {
                // Fetching related Repair data
                RepairDAO repairDAO = new RepairDAO();
                Repair repair = repairDAO.getRepairById(resultSet.getInt("repair_id"));

                retourList.add(new Retour(
                        resultSet.getInt("retour_id"),
                        resultSet.getTimestamp("retour_date"),
                        repair
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all retours: " + e.getMessage());
        }
        return retourList;
    }
}
