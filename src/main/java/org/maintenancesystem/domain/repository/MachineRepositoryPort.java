package org.maintenancesystem.domain.repository;

import org.maintenancesystem.domain.model.entities.Machine;

import java.util.List;

public interface MachineRepositoryPort {

    void registerMachine(Machine machine);
    List<Machine> getAllMachines();
    boolean verifyMachineIfNameAlreadyExists(String name);
    Machine getMachineById(Long id);
}
