package org.maintenancesystem.domain.port.MaintenanceRequest;

import org.maintenancesystem.domain.model.entities.MaintenanceRequest;
import org.maintenancesystem.domain.model.enums.MachineStatus;
import org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus;

import java.sql.SQLException;
import java.util.List;

public interface MaintenanceRepositoryPort {

    void registerMaintenanceRequest(MaintenanceRequest maintenanceRequest) throws SQLException;
    List<MaintenanceRequest> getAllPendingMaintenanceRequests() throws SQLException;
    MaintenanceRequest getPendingMaintenanceById(Long id) throws SQLException;
    boolean updateMaintenanceStatus(Long id, MaintenanceRequestStatus maintenanceRequestStatus) throws SQLException;
}
