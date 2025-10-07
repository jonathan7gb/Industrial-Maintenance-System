package org.maintenancesystem.infrastructure.persistence;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.domain.model.enums.MachineStatus;
import org.maintenancesystem.domain.repository.MachineRepositoryPort;
import org.maintenancesystem.infrastructure.configuration.ConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MachineRepositoryImplements implements MachineRepositoryPort {

    @Override
    public void registerMachine(Machine machine) throws SQLException {
        String command = "INSERT INTO Maquina (nome, setor, status) VALUES (?, ?, ?)";

        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, machine.getName());
            stmt.setString(2, machine.getSector());
            stmt.setString(3, machine.getStatus().toString());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Machine> getAllMachines() throws SQLException {
        String command = "SELECT id, nome, setor, status FROM Maquina";

        List<Machine> machines = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command);
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
        String command = "SELECT nome FROM Maquina WHERE nome = ?";

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }

            return false;
        }
    }

    @Override
    public Machine getMachineById(Long id) throws SQLException {
        String command = "SELECT id, nome, setor, status FROM Maquina WHERE id = ?";

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setLong(1, id);
            if (rs.next()) {
                String name = rs.getString("nome");
                String sector = rs.getString("setor");
                String status = rs.getString("status");
                MachineStatus machineStatus = MachineStatus.valueOf(status);
                return new Machine(id, name, sector, machineStatus);
            }

            return null;
        }
    }
}
