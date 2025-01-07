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
import org.example.techcare.model.repair.RepairDAO;
import org.example.techcare.model.repair.RepairStatusDAO;
import org.example.techcare.model.repair.RepairStatus;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RepaireServlet", value = "/repaireServlet")
public class RepairServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        List<TypeComponent> listTypeComponents = new TypeComponentDAO().getAllTypeComponents();
        request.setAttribute("TypeComponent",listTypeComponents);
        RequestDispatcher dispastcher = request.getRequestDispatcher("pages/listReparation.jsp");
        dispastcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idTypeComponent =Integer.parseInt(req.getParameter("typComponentId")) ;
        List<Repair> repairs = new RepairDAO().getRepairsByTypeComponentId(idTypeComponent);
        List<RepairStatus> reparationStatuses = new RepairStatusDAO().getAllRepairStatuses();
        req.setAttribute("repairs",repairs);
        req.setAttribute("repairstatus",reparationStatuses);
        RequestDispatcher dispastcher =req.getRequestDispatcher("pages/listReparation.jsp");
        dispastcher.forward(req,resp);
    }

    public void destroy() {
    }
}