package org.example.techcare.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.techcare.dao.ComponentDAO;
import org.example.techcare.dao.ComponentRecommandeDAO;
import org.example.techcare.model.ComponentRecommande;
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

        // INSERT operation

        try {
            if (mode.equals("insert")) {
                int componentId = Integer.parseInt(req.getParameter("componentId"));
                Date dateRecommande = Date.valueOf(req.getParameter("dateRecommande"));

                // Retrieve the component by its ID
                Component component = componentDAO.getComponentById(componentId);

                // Create a new ComponentRecommande object
                ComponentRecommande componentRecommande = new ComponentRecommande();
                componentRecommande.setComponent(component);
                componentRecommande.setDate_recommande(dateRecommande);

                // Insert into the database
                componentRecommandeDAO.createComponentRecommande(componentRecommande);
                System.out.println("insertion componentRecommande");
                // Success message
                req.setAttribute("message", "ComponentRecommande successfully inserted.");
                req.setAttribute("page", "insertComposantRecommande");

            }else if ( mode.equals("S") ) {
                try {
                    List<ComponentRecommande> componentRecommandes = new ArrayList<>();
                    try {
                        if (req.getParameter("dateRecommande") != null && !req.getParameter("dateRecommande").isEmpty()) {
                            Date dateRecommande = Date.valueOf(req.getParameter("dateRecommande"));
                            componentRecommandes = componentRecommandeDAO.getByMonth(dateRecommande);
                            System.out.println("getByMonth");
                        } else {
                            componentRecommandes = componentRecommandeDAO.getAllComponentRecommandes();
                            System.out.println("getAll");
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid dateRecommande parameter: " + req.getParameter("dateRecommande"));
                        req.setAttribute("error", "Invalid date format for dateRecommande");
                    }


                    // Pass the data to the request
                    req.setAttribute("componentRecommandes", componentRecommandes);

                    // Forward to the JSP for display
                    req.setAttribute("page", "listComposantRecommande");
                } catch (Exception e) {
                    req.setAttribute("error", "Error fetching ComponentRecommandes: " + e.getMessage());
                    System.out.println("Error fetching ComponentRecommandes: " + e.getMessage());
                }

            }else {
                req.setAttribute("page", "insertComposantRecommande");

            }

        } catch (Exception e) {
            req.setAttribute("error", "Error inserting ComponentRecommande: " + e.getMessage());
        }
        List<Component> components = new ComponentDAO().getAllComponents();
        req.setAttribute("components", components);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");

        // Default mode: list all

        if (mode == null) {
            List<Component> components = new ComponentDAO().getAllComponents();
            req.setAttribute("components", components);
            req.setAttribute("page", "insertComposantRecommande");

        }
        else if (mode.equals("list")){
            try {
                // Retrieve all ComponentRecommandes from the database
                List<ComponentRecommande> componentRecommandes = componentRecommandeDAO.getAllComponentRecommandes();

                // Pass the data to the request
                req.setAttribute("componentRecommandes", componentRecommandes);

                // Forward to the JSP for display
                req.setAttribute("page", "listComposantRecommande");
            } catch (Exception e) {
                req.setAttribute("error", "Error fetching ComponentRecommandes: " + e.getMessage());
            }
        }

        // Forward the request to the index.jsp for rendering
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
