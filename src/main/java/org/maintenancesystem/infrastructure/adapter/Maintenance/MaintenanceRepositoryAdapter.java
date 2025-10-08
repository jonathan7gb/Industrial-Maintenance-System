package org.maintenancesystem.infrastructure.adapter.Maintenance;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.entities.MaintenanceRequest;
import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus;
import org.maintenancesystem.domain.port.Maintenance.MaintenanceRepositoryPort;
import org.maintenancesystem.infrastructure.adapter.Machine.MachineRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.Technician.TechnicianRepositoryAdapter;
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
        String command = """
            SELECT
                OM.id,
                OM.dataSolicitacao,
                OM.status,
                M.nome AS nome_maquina,  
                T.nome AS nome_tecnico
            FROM
                OrdemManutencao AS OM
            INNER JOIN
                Maquina AS M ON OM.idMaquina = M.id
            INNER JOIN
                Tecnico AS T ON OM.idTecnico = T.id
            WHERE
                OM.status = 'PENDENTE';
        """;

        List<MaintenanceRequest> maintenanceRequest = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long ID = rs.getLong("id");
                Date data = rs.getDate("dataSolicitacao");
                MaintenanceRequestStatus status = MaintenanceRequestStatus.valueOf(rs.getString("status"));

                Machine machine = new Machine(rs.getString("nome_maquina"));
                Technician technician = new Technician(rs.getString("nome_tecnico"));
                LocalDate dataSolicitacao = data.toLocalDate();

                maintenanceRequest.add(new MaintenanceRequest(ID, machine, technician, dataSolicitacao,status));
            }

            return maintenanceRequest;
        }
    }

    @Override
    public MaintenanceRequest getPendingMaintenanceById(Long id) throws SQLException {
        String command = """
            SELECT
                OM.id,
                OM.dataSolicitacao,
                OM.status,
                M.nome AS nome_maquina,  
                T.nome AS nome_tecnico
            FROM
                OrdemManutencao AS OM
            INNER JOIN
                Maquina AS M ON OM.idMaquina = M.id
            INNER JOIN
                Tecnico AS T ON OM.idTecnico = T.id
            WHERE
                OM.id = ?
            AND
                OM.status = 'PENDENTE';
        """;

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Long ID = rs.getLong("id");
                Date data = rs.getDate("dataSolicitacao");
                MaintenanceRequestStatus status = MaintenanceRequestStatus.valueOf(rs.getString("status"));

                Machine machine = new Machine(rs.getString("nome_maquina"));
                Technician technician = new Technician(rs.getString("nome_tecnico"));
                LocalDate dataSolicitacao = data.toLocalDate();
                return new MaintenanceRequest(ID, machine, technician, dataSolicitacao,status);
            }

            return null;
        }
    }
}
