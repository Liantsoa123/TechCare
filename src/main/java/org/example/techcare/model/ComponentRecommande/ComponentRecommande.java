package org.example.techcare.model.ComponentRecommande;
import org.example.techcare.model.component.Component;

import java.sql.Date;

public class ComponentRecommande {
    private int id;
    private  Component component;
    private Date date_recommande;

    public ComponentRecommande(int id, Date date_recommande, Component component) {
        this.id = id;
        this.date_recommande = date_recommande;
        this.component = component;
    }

    public ComponentRecommande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Date getDate_recommande() {
        return date_recommande;
    }

    public void setDate_recommande(Date date_recommande) {
        this.date_recommande = date_recommande;
    }
}
