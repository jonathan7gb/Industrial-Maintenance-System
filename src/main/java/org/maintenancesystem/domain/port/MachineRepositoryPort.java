package org.maintenancesystem.domain.port;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.enums.MachineStatus;

import java.sql.SQLException;
import java.util.List;

public interface MachineRepositoryPort {

    void registerMachine(Machine machine) throws SQLException;
    List<Machine> getAllMachines() throws SQLException;
    List<Machine> getAllOperationalMachines() throws SQLException;
    boolean verifyMachineIfNameAlreadyExists(String name) throws SQLException;
    Machine getMachineById(Long id) throws SQLException;
    boolean updateMachineStatus(Long id, MachineStatus machineStatus) throws SQLException;
}
