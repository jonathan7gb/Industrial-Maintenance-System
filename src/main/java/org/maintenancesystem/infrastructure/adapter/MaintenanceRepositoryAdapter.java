package org.maintenancesystem.infrastructure.adapter;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.entities.MaintenanceRequest;
import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.domain.model.enums.MachineStatus;
import org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus;
import org.maintenancesystem.domain.port.MaintenanceRepositoryPort;
import org.maintenancesystem.infrastructure.configuration.ConnectionDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceRepositoryAdapter implements MaintenanceRepositoryPort {

    MachineRepositoryAdapter machineRepositoryAdapter =  new MachineRepositoryAdapter();
    TechnicianRepositoryAdapter technicianRepositoryAdapter =  new TechnicianRepositoryAdapter();

    @Override
    public void registerMaintenanceRequest(MaintenanceRequest maintenanceRequest) throws SQLException {

        String command = "INSERT INTO OrdemManutencao (idMaquina, idTecnico, status) VALUES (?, ?, ?)";

        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, maintenanceRequest.getMachine().getID());
            stmt.setLong(2, maintenanceRequest.getTechnical().getID());
            stmt.setString(3, MaintenanceRequestStatus.PENDENTE.name());
            stmt.executeUpdate();
        }
    }

    public List<MaintenanceRequest> getAllPendingMaintenanceRequests() throws SQLException{
        String command = "SELECT id, idMaquina, idTecnico, dataSolicitacao, status FROM OrdemManutencao WHERE status = 'PENDENTE'";

        List<MaintenanceRequest> maintenanceRequest = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long ID = rs.getLong("id");
                Long machineID = rs.getLong("idMaquina");
                Long technicianID = rs.getLong("idTecnico");
                Date data = rs.getDate("dataSolicitacao");
                MaintenanceRequestStatus status = MaintenanceRequestStatus.valueOf(rs.getString("status"));

                Machine machine = machineRepositoryAdapter.getMachineById(machineID);
                Technician technician = technicianRepositoryAdapter.getTechnicianById(technicianID);
                LocalDate dataSolicitacao = data.toLocalDate();

                maintenanceRequest.add(new MaintenanceRequest(ID, machine, technician, dataSolicitacao,status));
            }

            return maintenanceRequest;
        }
    }
}
