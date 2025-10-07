package org.maintenancesystem.infrastructure.adapter;

import org.maintenancesystem.domain.model.entities.MaintenanceRequest;
import org.maintenancesystem.domain.port.MaintenanceRepositoryPort;
import org.maintenancesystem.infrastructure.configuration.ConnectionDatabase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaintenanceRepositoryAdapter implements MaintenanceRepositoryPort {
    @Override
    public void registerMaintenanceRequest(MaintenanceRequest maintenanceRequest) throws SQLException {

        String command = "INSERT INTO OrdemManutencao (idMaquina, idTecnico, dataSolicitacao, status) VALUES (?, ?, ?, ?)";

        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, maintenanceRequest.getMachine().getID());
            stmt.setLong(2, maintenanceRequest.getTechnical().getID());
            stmt.setDate(3, Date.valueOf(maintenanceRequest.getRequestDate()));
            stmt.setString(4, maintenanceRequest.getStatus().toString());
            stmt.executeUpdate();
        }
    }
}
