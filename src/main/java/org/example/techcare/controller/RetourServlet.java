package org.example.techcare.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.techcare.dao.LaptopTypeDAO;
import org.example.techcare.dao.RepairDAO;
import org.example.techcare.dao.RetourDAO;
import org.example.techcare.dao.TypeComponentDAO;
import org.example.techcare.model.component.TypeComponent;
import org.example.techcare.model.laptotype.LaptopType;
import org.example.techcare.model.repair.Repair;
import org.example.techcare.model.retour.Retour;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static javax.swing.text.html.HTML.Tag.HEAD;

@WebServlet(name = "RetourServlet", value = "/retourServlet")
public class RetourServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        //INSERTION
        if (mode==null){
            Timestamp dateRetour = Timestamp.valueOf(req.getParameter("retourDate"));
            int repairId = Integer.parseInt(req.getParameter("repairId"));
            Repair repair = new RepairDAO().getRepairById(repairId);
            Retour retour = new Retour(1,dateRetour, repair);
            new RetourDAO().createRetour(retour);
            System.out.println("insertion Retour");
        } else if (mode.equals("S")) {
            int typeComponentId = Integer.parseInt(req.getParameter("typecomponentId"));
            int laptopTypeId = Integer.parseInt(req.getParameter("laptopTypeId"));
            Boolean isUpgrade = req.getParameter("repairType")== "true";
            List<Retour> retourList = new RetourDAO().getRetourByTypeComponentIdAndTypeLaptopId(typeComponentId, laptopTypeId, isUpgrade);
            List<TypeComponent> typeComponents = new TypeComponentDAO().getAllTypeComponents();
            List<LaptopType> laptopTypes = new LaptopTypeDAO().getAllLaptopTypes();
            req.setAttribute("retours", retourList);
            req.setAttribute("typecomponents", typeComponents);
            req.setAttribute("laptoptypes", laptopTypes);
            req.setAttribute("page", "listRetour");
            System.out.println("Search Retour");
        } else if (mode.equals("SC")){
            java.sql.Date retourDate = java.sql.Date.valueOf(req.getParameter("retourDate"));
            List<Retour> retourList  = new RetourDAO().getByDate(retourDate );
            req.setAttribute("retours", retourList);req.setAttribute("page", "listAchatClient");

        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        if ( mode == null){
            List<Repair> repairs = new RepairDAO().getAllRepairs();
            req.setAttribute("repairs", repairs);
            req.setAttribute("page","insertRetour");
            System.out.println("Go to Retour insertion");

        } else if (mode.equals("L")) {
            List<TypeComponent> typeComponents = new TypeComponentDAO().getAllTypeComponents();
            List<LaptopType> laptopTypes = new LaptopTypeDAO().getAllLaptopTypes();
            req.setAttribute("typecomponents", typeComponents);
            req.setAttribute("laptoptypes", laptopTypes);
            req.setAttribute("page","listRetour");
            System.out.println("Go to Retour list");

        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);

    }
}
