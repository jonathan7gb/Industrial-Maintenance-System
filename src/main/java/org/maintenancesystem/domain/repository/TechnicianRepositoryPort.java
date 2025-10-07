package org.maintenancesystem.domain.repository;

import org.maintenancesystem.domain.model.entities.Technician;

import java.sql.SQLException;
import java.util.List;

public interface TechnicianRepositoryPort {

    void registerTechnician(Technician technician) throws SQLException;
    List<Technician> getAllTechnicians() throws SQLException;
    boolean verifyTechnicianIfNameAlreadyExists(String name) throws SQLException;
    Technician getTechnicianById(Long id) throws SQLException;
}
