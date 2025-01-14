package org.example.techcare.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.techcare.dao.ComponentDAO;
import org.example.techcare.dao.ComponentRecommandeDAO;
import org.example.techcare.model.ComponentRecommande.ComponentRecommande;
import org.example.techcare.model.component.Component;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ComponentRecommandeServlet", value = "/componentRecommandeServlet")
public class ComponentRecommandeServlet extends HttpServlet {

    private final ComponentRecommandeDAO componentRecommandeDAO = new ComponentRecommandeDAO();
    private final ComponentDAO componentDAO = new ComponentDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");


        if (mode.equals("insert")) {
            int componentId = Integer.parseInt(req.getParameter("componentId"));
            Date dateRecommande = Date.valueOf(req.getParameter("dateRecommande"));
            Component component = componentDAO.getComponentById(componentId);
            ComponentRecommande componentRecommande = new ComponentRecommande(1, dateRecommande, component);
            componentRecommandeDAO.createComponentRecommande(componentRecommande);
            System.out.println("insertion componentRecommande");
            req.setAttribute("message", "ComponentRecommande successfully inserted.");
            req.setAttribute("page", "insertComposantRecommande");

        } else if (mode.equals("S")) {

            List<ComponentRecommande> componentRecommandes = new ArrayList<>();
            if (req.getParameter("dateRecommande") != null && !req.getParameter("dateRecommande").isEmpty()) {
                Date dateRecommande = Date.valueOf(req.getParameter("dateRecommande"));
                componentRecommandes = componentRecommandeDAO.getByMonth(dateRecommande);
                System.out.println("getByMonth");
            } else {
                componentRecommandes = componentRecommandeDAO.getAllComponentRecommandes();
                System.out.println("getAll");
            }
            req.setAttribute("componentRecommandes", componentRecommandes);
            req.setAttribute("page", "listComposantRecommande");

        } else {
            req.setAttribute("page", "insertComposantRecommande");
        }

        List<Component> components = new ComponentDAO().getAllComponents();
        req.setAttribute("components", components);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        if (mode == null) {
            List<Component> components = new ComponentDAO().getAllComponents();
            req.setAttribute("components", components);
            req.setAttribute("page", "insertComposantRecommande");

        } else if (mode.equals("list")) {

            List<ComponentRecommande> componentRecommandes = componentRecommandeDAO.getAllComponentRecommandes();
            req.setAttribute("componentRecommandes", componentRecommandes);
            req.setAttribute("page", "listComposantRecommande");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
