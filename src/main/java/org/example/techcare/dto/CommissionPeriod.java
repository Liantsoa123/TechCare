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

}
