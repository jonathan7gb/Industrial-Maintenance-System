package org.maintenancesystem.domain.repository;

import org.maintenancesystem.domain.model.entities.Technician;

import java.sql.SQLException;
import java.util.List;

public interface TechnicianRepositoryPort {

    void registerTechnical(Technician technician) throws SQLException;
    List<Technician> getAllTechnicals() throws SQLException;
    boolean verifyTechnicalIfNameAlreadyExists(String name) throws SQLException;
    Technician getTechnicalById(Long id) throws SQLException;
}
