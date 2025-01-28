package org.example.techcare.model.histo;

import org.example.techcare.model.component.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HistoriquePrix {
    private int id;
    private LocalDate dateHisto;
    private BigDecimal prix;
    private Component component;

    public HistoriquePrix() {
    }

    public HistoriquePrix(int id, LocalDate dateHisto, BigDecimal prix, Component component) {
        this.id = id;
        this.dateHisto = dateHisto;
        this.prix = prix;
        this.component = component;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateHisto() {
        return dateHisto;
    }

    public void setDateHisto(LocalDate dateHisto) {
        this.dateHisto = dateHisto;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
