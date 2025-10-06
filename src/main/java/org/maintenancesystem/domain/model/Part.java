package org.maintenancesystem.domain.model.entities;

public class Part {

    private Long ID;
    private String name;
    private String quantityInStock;

    public Part(Long ID, String name, String quantityInStock) {
        this.ID = ID;
        this.name = name;
        this.quantityInStock = quantityInStock;
    }

    public Part(String name, String quantityInStock) {
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

    public String getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(String quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
