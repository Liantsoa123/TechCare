package org.example.techcare.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.techcare.dao.BrandComponentDAO;
import org.example.techcare.dao.ComponentDAO;
import org.example.techcare.dao.TypeComponentDAO;
import org.example.techcare.model.brandcomponent.BrandComponent;
import org.example.techcare.model.component.Component;
import org.example.techcare.model.component.TypeComponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "ComponentServlet", urlPatterns = "/componentServlet")
public class ComponentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        //Go to insert page
        List<TypeComponent> typeComponentList = new TypeComponentDAO().getAllTypeComponents();
        List<BrandComponent> brandComponents = new BrandComponentDAO().getAllBrandComponents();
        req.setAttribute("typeComponents", typeComponentList);
        req.setAttribute("brandComponents", brandComponents);

        if (mode == null) {
            req.setAttribute("page", "insertComponent");
        } else if (mode.equals("list")) {
            req.setAttribute("page", "listComponent");
        }
        RequestDispatcher dispastcher = req.getRequestDispatcher("index.jsp");
        dispastcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        List<TypeComponent> typeComponentList = new TypeComponentDAO().getAllTypeComponents();
        List<BrandComponent> brandComponents = new BrandComponentDAO().getAllBrandComponents();
        req.setAttribute("typeComponents", typeComponentList);
        req.setAttribute("brandComponents", brandComponents);
        List<Component> components = new ComponentDAO().getAllComponents();

        if (mode.equals("insert")) {
            String model = req.getParameter("model");
            BigDecimal capacity = new BigDecimal(req.getParameter("capacity"));
            BigDecimal unite_price = new BigDecimal(1);
            int brandComponentId = Integer.parseInt(req.getParameter("brandComponentId"));
            BrandComponent brandComponent = new BrandComponentDAO().getBrandComponentById(brandComponentId);
            int typeComponentId = Integer.parseInt(req.getParameter("typeComponentId"));
            TypeComponent typeComponent = new TypeComponentDAO().getTypeComponentById(typeComponentId);
            Component component = new Component(0, unite_price, capacity, typeComponent, brandComponent, model);
            new ComponentDAO().createComponent(component);
        } else if (mode.equals("search")) {
            int typeComponentId = Integer.parseInt(req.getParameter("typeComponentId"));
            int brandComponentId = Integer.parseInt(req.getParameter("brandComponentId"));
            //Recherche


        }

        req.setAttribute("components", components);
        req.setAttribute("page", "listComponent");
        RequestDispatcher dispastcher = req.getRequestDispatcher("index.jsp");
        dispastcher.forward(req, resp);
    }
}
