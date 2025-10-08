package org.maintenancesystem.domain.port.Maintenance;

import org.maintenancesystem.domain.model.entities.MaintenanceRequest;

import java.sql.SQLException;
import java.util.List;

public interface MaintenanceRepositoryPort {

    void registerMaintenanceRequest(MaintenanceRequest maintenanceRequest) throws SQLException;

    List<MaintenanceRequest> getAllPendingMaintenanceRequests() throws SQLException;

    MaintenanceRequest getPendingMaintenanceById(Long id) throws SQLException;
}
