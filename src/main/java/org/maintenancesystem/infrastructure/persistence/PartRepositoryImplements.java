package org.maintenancesystem.infrastructure.persistence;

import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.domain.repository.PartRepositoryPort;

import java.sql.SQLException;
import java.util.List;

public class PartRepositoryImplements implements PartRepositoryPort {
    @Override
    public void registerPart(Part part) throws SQLException {

    }

    @Override
    public List<Part> getAllParts() throws SQLException {
        return List.of();
    }

    @Override
    public boolean verifyPartIfNameAlreadyExists(String name) throws SQLException {
        return false;
    }

    @Override
    public Part getPartById(Long id) throws SQLException {
        return null;
    }
}
