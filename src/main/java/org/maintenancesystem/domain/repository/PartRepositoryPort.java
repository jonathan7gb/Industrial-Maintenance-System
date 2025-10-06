package org.maintenancesystem.domain.repository;

import org.maintenancesystem.domain.model.entities.Part;

import java.sql.SQLException;
import java.util.List;

public interface PartRepositoryPort {

    void registerPart(Part part) throws SQLException;
    List<Part> getAllParts() throws SQLException;
    boolean verifyPartIfNameAlreadyExists(String name) throws SQLException;
    Part getPartById(Long id) throws SQLException;
}
