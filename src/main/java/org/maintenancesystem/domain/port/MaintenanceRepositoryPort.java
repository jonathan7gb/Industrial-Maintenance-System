package org.maintenancesystem.domain.port;

import org.maintenancesystem.domain.model.entities.MaintenanceRequest;

import java.sql.SQLException;

public interface MaintenanceRepositoryPort {

    void registerMaintenanceRequest(MaintenanceRequest maintenanceRequest) throws SQLException;
}
