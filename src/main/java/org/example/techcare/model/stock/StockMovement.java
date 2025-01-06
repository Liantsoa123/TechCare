package org.example.techcare.model.stock;

import org.example.techcare.model.component.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class StockMovement {
    private int stockMovementId;
    private BigDecimal quantity;
    private boolean isEnter;
    private Timestamp dateMovement;
    private Component component;

    public StockMovement() {
    }

    public StockMovement(int stockMovementId, BigDecimal quantity, boolean isEnter, Timestamp dateMovement, Component component) {
        this.stockMovementId = stockMovementId;
        this.quantity = quantity;
        this.isEnter = isEnter;
        this.dateMovement = dateMovement;
        this.component = component;
    }

    public int getStockMovementId() {
        return stockMovementId;
    }

    public void setStockMovementId(int stockMovementId) {
        this.stockMovementId = stockMovementId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public boolean isEnter() {
        return isEnter;
    }

    public void setEnter(boolean enter) {
        isEnter = enter;
    }

    public Timestamp getDateMovement() {
        return dateMovement;
    }

    public void setDateMovement(Timestamp dateMovement) {
        this.dateMovement = dateMovement;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
