package org.maintenancesystem.infrastructure.persistence;

import org.maintenancesystem.domain.model.entities.Technical;
import org.maintenancesystem.domain.repository.TechnicalRepositoryPort;

import java.util.List;

public class TechnicalRepositoryImplements implements TechnicalRepositoryPort {
    @Override
    public void registerTechnical(Technical technical) {

    }

    @Override
    public List<Technical> getAllTechnicals() {
        return List.of();
    }

    @Override
    public boolean verifyTechnicalIfNameAlreadyExists(String name) {
        return false;
    }

    @Override
    public Technical getTechnicalById(Long id) {
        return null;
    }
}
