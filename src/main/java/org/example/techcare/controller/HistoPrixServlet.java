package org.example.techcare.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.techcare.dao.ComponentDAO;
import org.example.techcare.dao.HistoriquePrixDAO;
import org.example.techcare.model.component.Component;
import org.example.techcare.model.histo.HistoriquePrix;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HistoPrixServlet", value = "/HistoPrixServlet")
public class HistoPrixServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        if ( mode == null){
            List<Component> components = new ComponentDAO().getAllComponents();
            req.setAttribute("components", components);
            req.setAttribute("page" ,"insertHisto");
        }
        else if ( mode.equals("list") ){
            List<Component> components = new ComponentDAO().getAllComponents();
            List<HistoriquePrix> historiquePrixes = new HistoriquePrixDAO().getAllHistoriquePrix();
            req.setAttribute("components", components);
            req.setAttribute("page" ,"listHisto");
            req.setAttribute("historiques" , historiquePrixes);
        }
        RequestDispatcher dispastcher = req.getRequestDispatcher("index.jsp");
        dispastcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        List<Component> components = new ComponentDAO().getAllComponents();
        List<HistoriquePrix> historiquePrixes = new HistoriquePrixDAO().getAllHistoriquePrix();
        //Insertion
        if ( mode == null){
            BigDecimal prix  = new BigDecimal(req.getParameter("prix"));
            Date date = Date.valueOf(req.getParameter("date"));
            int idComponent = Integer.parseInt(req.getParameter("componentId"));
            Component component = new ComponentDAO().getComponentById(idComponent);
            HistoriquePrix historiquePrix = new HistoriquePrix(0 , date.toLocalDate(), prix, component);
            new HistoriquePrixDAO().createHistoriquePrix(historiquePrix);
        }
        //SEARCH
        else if ( mode.equals("search") ) {
            int idComponent = Integer.parseInt(req.getParameter("componentId"));
            if ( idComponent > 0  ){
                historiquePrixes = new HistoriquePrixDAO().getHistoriqueByIdComponent(idComponent);
            }

        }
        System.out.println("size histo " + historiquePrixes.size());
        req.setAttribute("components", components);
        req.setAttribute("page" ,"listHisto");
        req.setAttribute("historiques" , historiquePrixes);
        RequestDispatcher dispastcher = req.getRequestDispatcher("index.jsp");
        dispastcher.forward(req, resp);
    }
}
