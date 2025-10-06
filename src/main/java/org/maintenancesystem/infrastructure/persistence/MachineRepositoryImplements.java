package org.maintenancesystem.infrastructure.persistence;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.enums.MachineStatus;
import org.maintenancesystem.domain.repository.MachineRepositoryPort;
import org.maintenancesystem.infrastructure.configuration.ConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MachineRepositoryImplements implements MachineRepositoryPort {

    @Override
    public void registerMachine(Machine machine) throws SQLException {
        String comando = "INSERT INTO Maquina (nome, setor, status) VALUES (?, ?, ?)";

        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(comando)) {
            stmt.setString(1, machine.getName());
            stmt.setString(2, machine.getSector());
            stmt.setObject(3, machine.getStatus());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Machine> getAllMachines() throws SQLException {
        String comando = "SELECT id, nome, setor, status FROM Maquina";

        List<Machine> machines = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(comando);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long ID = rs.getLong("id");
                String name = rs.getString("nome");
                String sector = rs.getString("setor");
                MachineStatus status = MachineStatus.valueOf(rs.getString("status"));

                machines.add(new Machine(ID, name, sector, status));
            }

            return machines;
        }
    }

    @Override
    public boolean verifyMachineIfNameAlreadyExists(String name) throws SQLException{
        return false;
    }

    @Override
    public Machine getMachineById(Long id) throws SQLException {
        return null;
    }
}
