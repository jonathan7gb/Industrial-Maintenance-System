package org.maintenancesystem.domain.repository;

import org.maintenancesystem.domain.model.entities.Technical;

import java.sql.SQLException;
import java.util.List;

public interface TechnicalRepositoryPort {

    void registerTechnical(Technical technical) throws SQLException;
    List<Technical> getAllTechnicals() throws SQLException;
    boolean verifyTechnicalIfNameAlreadyExists(String name) throws SQLException;
    Technical getTechnicalById(Long id) throws SQLException;
}
