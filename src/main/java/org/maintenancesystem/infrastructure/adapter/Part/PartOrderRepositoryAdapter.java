package org.maintenancesystem.infrastructure.adapter.Part;

import org.maintenancesystem.domain.port.Part.PartOrderRepositoryPort;
import org.maintenancesystem.infrastructure.adapter.Machine.MachineRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.Technician.TechnicianRepositoryAdapter;
import org.maintenancesystem.infrastructure.configuration.ConnectionDatabase;

import java.sql.*;

public class PartOrderRepositoryAdapter implements PartOrderRepositoryPort {

    MachineRepositoryAdapter machineRepositoryAdapter =  new MachineRepositoryAdapter();
    TechnicianRepositoryAdapter technicianRepositoryAdapter =  new TechnicianRepositoryAdapter();

    @Override
    public void registerPartOrder(Long orderID, Long partID, double quantity) throws SQLException {

        String command = "INSERT INTO OrdemPeca (idOrdem, idPeca, quantidade) VALUES (?, ?, ?)";

        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, orderID);
            stmt.setLong(2, partID);
            stmt.setDouble(3, quantity);
            stmt.executeUpdate();
        }
    }

//    public List<String> getAllPartOrders() throws SQLException{
//        String command = """
//            SELECT
//                OM.id,
//                OM.dataSolicitacao,
//                OM.status,
//                M.nome AS nome_maquina,
//                T.nome AS nome_tecnico
//            FROM
//                OrdemManutencao AS OM
//            INNER JOIN
//                Maquina AS M ON OM.idMaquina = M.id
//            INNER JOIN
//                Tecnico AS T ON OM.idTecnico = T.id
//            WHERE
//                OM.status = 'PENDENTE';
//        """;
//
//        List<MaintenanceRequest> maintenanceRequest = new ArrayList<>();
//
//        try (Connection conn = ConnectionDatabase.connect();
//             PreparedStatement stmt = conn.prepareStatement(command);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                Long ID = rs.getLong("id");
//                Date data = rs.getDate("dataSolicitacao");
//                MaintenanceRequestStatus status = MaintenanceRequestStatus.valueOf(rs.getString("status"));
//
//                Machine machine = new Machine(rs.getString("nome_maquina"));
//                Technician technician = new Technician(rs.getString("nome_tecnico"));
//                LocalDate dataSolicitacao = data.toLocalDate();
//
//                maintenanceRequest.add(new MaintenanceRequest(ID, machine, technician, dataSolicitacao,status));
//            }
//
//            return maintenanceRequest;
//        }
//    }


}
