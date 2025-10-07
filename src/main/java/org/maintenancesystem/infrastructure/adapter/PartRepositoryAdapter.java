package org.maintenancesystem.infrastructure.adapter;

import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.domain.port.PartRepositoryPort;
import org.maintenancesystem.infrastructure.configuration.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartRepositoryAdapter implements PartRepositoryPort {
    @Override
    public void registerPart(Part part) throws SQLException {
        String command = "INSERT INTO Peca (nome, estoque) VALUES (?, ?)";

        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, part.getName());
            stmt.setDouble(2, part.getQuantityInStock());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Part> getAllParts() throws SQLException {
        String command = "SELECT id, nome, estoque FROM Peca";

        List<Part> parts = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long ID = rs.getLong("id");
                String name = rs.getString("nome");
                double quantityInStock = rs.getDouble("estoque");

                parts.add(new Part(ID, name, quantityInStock));
            }

            return parts;
        }
    }

    @Override
    public List<Part> getAllPartsStockIsMoreThan0() throws SQLException {
        String command = "SELECT id, nome, estoque FROM Peca WHERE estoque > 0";

        List<Part> parts = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long ID = rs.getLong("id");
                String name = rs.getString("nome");
                double quantityInStock = rs.getDouble("estoque");

                parts.add(new Part(ID, name, quantityInStock));
            }

            return parts;
        }
    }

    @Override
    public boolean verifyPartIfNameAlreadyExists(String name) throws SQLException {
        String command = "SELECT nome FROM Peca WHERE nome = ?";

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
    public Part getPartById(Long id) throws SQLException {
        String command = "SELECT id, nome, estoque FROM Peca WHERE id = ?";

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("nome");
                double quantityInStock = rs.getDouble("estoque");
                return new Part(id, name, quantityInStock);
            }

            return null;
        }
    }

    @Override
    public Part getPartByIdStockIsMoreThan0(Long id) throws SQLException {
        String command = "SELECT id, nome, estoque FROM Peca WHERE id = ? AND estoque > 0";

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("nome");
                double quantityInStock = rs.getDouble("estoque");
                return new Part(id, name, quantityInStock);
            }

            return null;
        }
    }
}
