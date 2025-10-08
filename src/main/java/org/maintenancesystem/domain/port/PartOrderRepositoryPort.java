package org.maintenancesystem.domain.port;

import org.maintenancesystem.domain.model.entities.MaintenanceRequest;

import java.sql.SQLException;
import java.util.List;

public interface PartOrderRepositoryPort {

    void registerPartOrder(Long orderID, Long partID, double quantity) throws SQLException;

//    List<String> getAllPartOrders() throws SQLException;

}
