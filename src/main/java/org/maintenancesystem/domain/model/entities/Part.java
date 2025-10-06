package org.maintenancesystem.domain.model.entities;

public class Part {

    private Long ID;
    private String name;
    private double quantityInStock;

    public Part(Long ID, String name, double quantityInStock) {
        this.ID = ID;
        this.name = name;
        this.quantityInStock = quantityInStock;
    }

    public Part(String name, double quantityInStock) {
        this.name = name;
        this.quantityInStock = quantityInStock;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(double quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
