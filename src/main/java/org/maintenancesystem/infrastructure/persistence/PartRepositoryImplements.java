package org.maintenancesystem.infrastructure.persistence;

import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.domain.repository.PartRepositoryPort;

import java.util.List;

public class PartRepositoryImplements implements PartRepositoryPort {
    @Override
    public void registerPart(Part part) {

    }

    @Override
    public List<Part> getAllParts() {
        return List.of();
    }

    @Override
    public boolean verifyPartIfNameAlreadyExists(String name) {
        return false;
    }

    @Override
    public Part getPartById(Long id) {
        return null;
    }
}
