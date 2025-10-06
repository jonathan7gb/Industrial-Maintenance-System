package org.maintenancesystem.domain.repository;

import org.maintenancesystem.domain.model.entities.Technical;

import java.util.List;

public interface TechnicalRepositoryPort {

    void registerTechnical(Technical technical);
    List<Technical> getAllTechnicals();
    boolean verifyTechnicalIfNameAlreadyExists(String name);
    Technical getTechnicalById(Long id);
}
