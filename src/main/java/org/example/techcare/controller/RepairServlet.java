package org.example.techcare.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.techcare.model.component.TypeComponent;
import org.example.techcare.model.component.TypeComponentDAO;
import org.example.techcare.model.repair.Repair;
import org.example.techcare.dao.RepairDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RepaireServlet", value = "/repaireServlet")
public class RepairServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        List<TypeComponent> listTypeComponents = new TypeComponentDAO().getAllTypeComponents();
        request.setAttribute("TypeComponent", listTypeComponents);
        request.setAttribute("page", "listRepair");
        RequestDispatcher dispastcher = request.getRequestDispatcher("index.jsp");
        dispastcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idTypeComponent = Integer.parseInt(req.getParameter("typComponentId"));
        List<Repair> repairs = new RepairDAO().getRepairsByTypeComponentId(idTypeComponent);
        req.setAttribute("repairs", repairs);

        List<TypeComponent> listTypeComponents = new TypeComponentDAO().getAllTypeComponents();
        req.setAttribute("TypeComponent", listTypeComponents);
        req.setAttribute("page", "listRepair");

        RequestDispatcher dispastcher = req.getRequestDispatcher("index.jsp");
        dispastcher.forward(req, resp);
    }

    public void destroy() {
    }
}