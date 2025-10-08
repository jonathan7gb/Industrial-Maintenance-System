package org.maintenancesystem.infrastructure.adapter.Part;

import org.maintenancesystem.domain.port.Part.PartOrderRepositoryPort;
import org.maintenancesystem.infrastructure.adapter.Machine.MachineRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.Technician.TechnicianRepositoryAdapter;
import org.maintenancesystem.infrastructure.configuration.ConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> getAllOrderParts(Long orderID) throws SQLException{
        String command = """
            SELECT
                P.nome AS nome_peca,
                OP.quantidade AS quantidade
            FROM
                OrdemPeca AS OP
            INNER JOIN
                Peca AS P ON OP.idPeca = P.id
            INNER JOIN
                OrdemManutencao AS OM ON OP.idOrdem = OM.id
            WHERE
                OM.status = 'PENDENTE'
            AND 
                OP.idOrdem  = ?;
        """;

        List<String> orderParts = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, orderID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String partName = rs.getString("nome_peca");
                double quantity = rs.getDouble("quantidade");
                orderParts.add(String.format("|| - %-35s || Quantidade: %.1f\n", partName, quantity));
            }

            return orderParts;
        }
    }


}
