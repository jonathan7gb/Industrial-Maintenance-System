package org.maintenancesystem.domain.repository;

import org.maintenancesystem.domain.model.entities.Machine;

import java.sql.SQLException;
import java.util.List;

public interface MachineRepositoryPort {

    void registerMachine(Machine machine) throws SQLException;
    List<Machine> getAllMachines() throws SQLException;
    boolean verifyMachineIfNameAlreadyExists(String name) throws SQLException;
    Machine getMachineById(Long id) throws SQLException;
}
