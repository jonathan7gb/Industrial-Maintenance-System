package org.maintenancesystem.domain.repository;

import org.maintenancesystem.domain.model.entities.Part;

import java.util.List;

public interface PartRepositoryPort {

    void registerPart(Part part);
    List<Part> getAllParts();
    boolean verifyPartIfNameAlreadyExists(String name);
    Part getPartById(Long id);
}
