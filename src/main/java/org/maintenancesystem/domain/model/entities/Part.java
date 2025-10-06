package org.maintenancesystem.domain.model.entities;

public class Part {

    private Long ID;
    private String name;
    private int quantityInStock;

    public Part(Long ID, String name, int quantityInStock) {
        this.ID = ID;
        this.name = name;
        this.quantityInStock = quantityInStock;
    }

    public Part(String name, int quantityInStock) {
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

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
