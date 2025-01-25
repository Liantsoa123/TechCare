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
    private BigDecimal totalCommission;



    public CommissionPeriod() {
    }

    public CommissionPeriod(Technician technician, BigDecimal total, BigDecimal totalCommission ) {
        this.technician = technician;
        this.total = total;
        this.totalCommission = totalCommission;

    }

    public BigDecimal getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(BigDecimal totalCommission) {
        this.totalCommission = totalCommission;
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

    public static List<CommissionPeriod> getCommissionByDateDebutAndDabeFinAndIdSexe(Date debut, Date fin, int id_sexe) {
        List<CommissionPeriod> commissionPeriods = new ArrayList<>();
        String sql = "SELECT r.technician_id as technician_id, sum(r.commission) as total_commssion, sum(total) as total   \n" +
                "from repair as r\n" +
                "         join technician as t on r.technician_id = t.technician_id\n" +
                "where 1 = 1";
        if (debut != null) {
            sql += " and DATE(r.filing_date) >= ?";
        }
        if (fin != null) {
            sql += " and DATE(r.filing_date) <= ?";
        }
        if (id_sexe != -1) {
            sql += " and t.sexe_id = ?";
        }
        sql += " group by r.technician_id";
        try (PreparedStatement statement = new ConnectionBdd().getConnection().prepareStatement(sql)) {
            int index = 1;
            if (debut != null) {
                statement.setDate(index++, debut);
            }
            if (fin != null) {
                statement.setDate(index++, fin);
            }
            if (id_sexe != -1) {
                statement.setInt(index, id_sexe);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Technician technician = new TechnicianDAO().getTechnicianById(resultSet.getInt("technician_id"));
                BigDecimal total = resultSet.getBigDecimal("total");
                BigDecimal totalCommission = resultSet.getBigDecimal("total_commssion");
                commissionPeriods.add(new CommissionPeriod(technician, total, totalCommission));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commissionPeriods;
    }
}

