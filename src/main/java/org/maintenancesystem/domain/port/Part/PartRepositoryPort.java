package org.maintenancesystem.domain.port.Part;

import org.maintenancesystem.domain.model.entities.Part;

import java.sql.SQLException;
import java.util.List;

public interface PartRepositoryPort {

    void registerPart(Part part) throws SQLException;
    List<Part> getAllParts() throws SQLException;
    List<Part> getAllPartsStockIsMoreThan0() throws SQLException;
    boolean verifyPartIfNameAlreadyExists(String name) throws SQLException;
    Part getPartById(Long id) throws SQLException;
    Part getPartByIdStockIsMoreThan0(Long id) throws SQLException;
    void updatePartStock(Long id, double quantityUse) throws SQLException;
}
