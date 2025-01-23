package org.example.techcare.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.techcare.dao.*;
import org.example.techcare.model.component.TypeComponent;
import org.example.techcare.model.laptop.Laptop;
import org.example.techcare.model.repair.Repair;
import org.example.techcare.model.repair.RepairStatus;
import org.example.techcare.model.repair.RepairType;
import org.example.techcare.model.technician.Technician;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "RepaireServlet", value = "/repaireServlet")
public class RepairServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String mode = request.getParameter("mode");
        if (mode != null && mode.equals("insert")) {
            List<RepairType> repairTypeList = new RepairTypeDAO().getAllRepairTypes();
            List<Technician> technicians = new TechnicianDAO().getAllTechnicians();
            List<Laptop> laptopList = new LaptopDAO().getAllLaptops();

            request.setAttribute("repairTypeList", repairTypeList);
            request.setAttribute("technicianList", technicians);
            request.setAttribute("laptopList", laptopList);
            request.setAttribute("page", "insertRepair");

        }
//        Go to the list of repairs page
        else {
            List<TypeComponent> listTypeComponents = new TypeComponentDAO().getAllTypeComponents();
            List<RepairType> listRepairTypes = new RepairTypeDAO().getAllRepairTypes();
            request.setAttribute("TypeComponent", listTypeComponents);
            request.setAttribute("repairTypeList", listRepairTypes);
            request.setAttribute("page", "listRepair");
        }

        RequestDispatcher dispastcher = request.getRequestDispatcher("index.jsp");
        dispastcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
//       Insertion repair
        if (mode != null && mode.equals("insert")) {
            Laptop laptop = new LaptopDAO().getLaptopById(Integer.parseInt(req.getParameter("laptopId")));
            RepairType repairType = new RepairTypeDAO().getRepairTypeById(Integer.parseInt(req.getParameter("repairTypeId")));
            Technician technician = new TechnicianDAO().getTechnicianById(Integer.parseInt(req.getParameter("technicianId")));
            Timestamp dateRepair = new Timestamp(System.currentTimeMillis());
            BigDecimal total = req.getParameter("total") != null ? new BigDecimal(req.getParameter("total")) : new BigDecimal(0);
            RepairStatus repairStatus = new RepairStatusDAO().getRepairStatusById(1);
            String desc = req.getParameter("description");
            Repair repair = new Repair(0, dateRepair,null, laptop, technician, repairStatus, total, repairType, desc);
            new RepairDAO().createRepair(repair);
            resp.sendRedirect("repaireServlet");
            return;
        }
//        Search Repair by TypeComponent ID
        else {
            int idTypeComponent = Integer.parseInt(req.getParameter("typComponentId"));
            int idRepairType = Integer.parseInt(req.getParameter("repairTypeId"));
            List<Repair> repairs = new RepairDAO().getRepairsByTypeComponentIdAndTypeRepairId(idTypeComponent, idRepairType);
            req.setAttribute("repairs", repairs);
            List<TypeComponent> listTypeComponents = new TypeComponentDAO().getAllTypeComponents();
            req.setAttribute("TypeComponent", listTypeComponents);
            List<RepairType> listRepairTypes = new RepairTypeDAO().getAllRepairTypes();
            req.setAttribute("repairTypeList", listRepairTypes);

            req.setAttribute("page", "listRepair");

            RequestDispatcher dispastcher = req.getRequestDispatcher("index.jsp");
            dispastcher.forward(req, resp);
        }
    }

    public void destroy() {
    }
}