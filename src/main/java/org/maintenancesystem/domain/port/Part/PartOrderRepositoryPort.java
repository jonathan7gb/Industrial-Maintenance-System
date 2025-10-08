package org.maintenancesystem.domain.port.Part;

import java.sql.SQLException;

public interface PartOrderRepositoryPort {

    void registerPartOrder(Long orderID, Long partID, double quantity) throws SQLException;

//    List<String> getAllPartOrders() throws SQLException;

}
