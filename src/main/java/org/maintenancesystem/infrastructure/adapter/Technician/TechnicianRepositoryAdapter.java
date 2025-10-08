package org.maintenancesystem.infrastructure.adapter.Technician;

import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.domain.port.Technician.TechnicianRepositoryPort;
import org.maintenancesystem.infrastructure.configuration.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TechnicianRepositoryAdapter implements TechnicianRepositoryPort {

    public void registerTechnician(Technician technician) throws SQLException {
        String command= "INSERT INTO Tecnico (nome, especialidade) VALUES (?, ?)";

        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, technician.getName());
            stmt.setString(2, technician.getSpecialty());
            stmt.executeUpdate();
        }
    }

    public List<Technician> getAllTechnicians() throws SQLException {
        String command = "SELECT id, nome, especialidade FROM Tecnico";

        List<Technician> technicians = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long ID = rs.getLong("id");
                String name = rs.getString("nome");
                String specialty = rs.getString("especialidade");

                technicians.add(new Technician(ID, name, specialty));
            }

            return technicians;
        }
    }

    @Override
    public boolean verifyTechnicianIfNameAlreadyExists(String name) throws SQLException {
        String command = "SELECT nome FROM Tecnico WHERE nome = ?";

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
    public Technician getTechnicianById(Long id) throws SQLException {
        String command = "SELECT id, nome, especialidade FROM Tecnico WHERE id = ?";

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("nome");
                String specialty = rs.getString("especialidade");
                return new Technician(id, name, specialty);
            }

            return null;
        }
    }
}
