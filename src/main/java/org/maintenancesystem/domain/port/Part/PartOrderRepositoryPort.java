package org.maintenancesystem.domain.port.Part;

import java.sql.SQLException;
import java.util.List;

public interface PartOrderRepositoryPort {

    void registerPartOrder(Long orderID, Long partID, double quantity) throws SQLException;

    List<String> getAllOrderParts(Long orderID) throws SQLException;

}
