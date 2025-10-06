package org.maintenancesystem.infrastructure.persistence;

import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.domain.repository.TechnicianRepositoryPort;

import java.util.List;

public class TechnicianRepositoryImplements implements TechnicianRepositoryPort {
    @Override
    public void registerTechnical(Technician technician) {

    }

    @Override
    public List<Technician> getAllTechnicals() {
        return List.of();
    }

    @Override
    public boolean verifyTechnicalIfNameAlreadyExists(String name) {
        return false;
    }

    @Override
    public Technician getTechnicalById(Long id) {
        return null;
    }
}
