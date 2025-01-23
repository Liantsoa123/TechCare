package org.example.techcare.dto;

import org.example.techcare.model.technician.Technician;
import org.example.techcare.dao.TechnicianDAO;
import org.example.techcare.model.utils.ConnectionBdd;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommissionPeriod {
    private Technician technician;
    private BigDecimal total;

    public CommissionPeriod() {
    }

    public CommissionPeriod(Technician technician, BigDecimal total) {
        this.technician = technician;
        this.total = total;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public static List<CommissionPeriod> getCommissionByDateDebutAndDateFin( Date dateDebut, Date dateFin) {
        List<CommissionPeriod> commissionPeriods = new ArrayList<>();
        String sql = "SELECT technician_id, (SUM(total) * 5 / 100 ) AS total_amount FROM repair " +
                "WHERE  DATE(filing_date) >= ? AND DATE(filing_date) <= ? " +
                "GROUP BY technician_id";

        try (Connection connection = new ConnectionBdd().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, dateDebut);
            statement.setDate(2, dateFin);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Technician  technician = new TechnicianDAO().getTechnicianById( resultSet.getInt("technician_id") );
                    BigDecimal totalAmount = resultSet.getBigDecimal("total_amount");
                    CommissionPeriod commissionPeriod = new CommissionPeriod(technician, totalAmount);
                    commissionPeriods.add(commissionPeriod);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving commission: " + e.getMessage());
        }

        return commissionPeriods;
    }

public static List<CommissionPeriod> getCommissionByDateDebutAndDateFin( Date dateDebut, Date dateFin, int id_sexe , String montant) {
    List<CommissionPeriod> commissionPeriods = new ArrayList<>();
    String sql ;
    Double montan = 0.0; 
    if ( montant ==null || montant.isEmpty() ){
         sql = "SELECT repair.technician_id as technician_id , (SUM(total) * 5 / 100 ) AS total_amount FROM repair\n" +
                "            join technician t on  repair.technician_id = t.technician_id\n" +
                "            WHERE  DATE(filing_date) >= ? AND DATE(filing_date) <= ? AND t.id_sexe = ? \n" +
                "            GROUP BY repair.technician_id";

    }else {
        montan = Double.valueOf(montant);
        sql = "SELECT repair.technician_id AS technician_id, (SUM(total) * 5 / 100) AS total_amount FROM repair JOIN technician t ON repair.technician_id = t.technician_id WHERE DATE(filing_date) >= ? AND DATE(filing_date) <= ? AND t.id_sexe = ? GROUP BY repair.technician_id HAVING (SUM(total) * 5 / 100) >= ?";
    }
    
    try (Connection connection = new ConnectionBdd().getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setDate(1, dateDebut);
        statement.setDate(2, dateFin);
        statement.setInt(3,id_sexe);
        if ( montan > 0  ){
            statement.setDouble(4, montan);
        }

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Technician  technician = new TechnicianDAO().getTechnicianById( resultSet.getInt("technician_id") );
                BigDecimal totalAmount = resultSet.getBigDecimal("total_amount");
                CommissionPeriod commissionPeriod = new CommissionPeriod(technician, totalAmount);
                commissionPeriods.add(commissionPeriod);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error while retrieving commission: " + e.getMessage());
    }

    return commissionPeriods;
}

}

