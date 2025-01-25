package org.example.techcare.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.techcare.dto.CommissionPeriod;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CommissionServlet", value = "/commissionServlet")
public class CommissionServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("page", "CommissionParVendeur");

        RequestDispatcher dispastcher = request.getRequestDispatcher("index.jsp");
        dispastcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date dateDebut = null;
        Date dateFin = null;
        int id_sexe = -1;
        // Pour dateDebut
        String paramDateDebut = req.getParameter("dateDebut");
        if (paramDateDebut != null && !paramDateDebut.trim().isEmpty()) {
            dateDebut = java.sql.Date.valueOf(paramDateDebut.trim());
            req.setAttribute("dateDebut", paramDateDebut);
        }

        // Pour dateFin
        String paramDateFin = req.getParameter("dateFin");
        if (paramDateFin != null && !paramDateFin.trim().isEmpty()) {
            dateFin = java.sql.Date.valueOf(paramDateFin.trim());
            req.setAttribute("dateFin", paramDateFin);
        }
        if (req.getParameter("id_sexe") != null) {
            id_sexe = Integer.parseInt(req.getParameter("id_sexe"));
            req.setAttribute("id_sexe", id_sexe);
        }
        List<CommissionPeriod> commissionPeriods = new ArrayList<>();
        commissionPeriods = CommissionPeriod.getCommissionByDateDebutAndDabeFinAndIdSexe(dateDebut, dateFin, id_sexe);
        req.setAttribute("commissions", commissionPeriods);
        System.out.println("commission" + commissionPeriods.size());
        for (CommissionPeriod commissionPeriod : commissionPeriods) {
            System.out.println("comm = " + commissionPeriod.getTechnician().getName());
        }
        req.setAttribute("page", "CommissionParVendeur");
        RequestDispatcher dispastcher = req.getRequestDispatcher("index.jsp");
        dispastcher.forward(req, resp);

    }
}