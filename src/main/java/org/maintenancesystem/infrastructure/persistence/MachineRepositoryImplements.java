package org.maintenancesystem.infrastructure.persistence;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.repository.MachineRepositoryPort;

import java.util.List;

public class MachineRepositoryImplements implements MachineRepositoryPort {
    @Override
    public void registerMachine(Machine machine) {

    }

    @Override
    public List<Machine> getAllMachines() {
        return List.of();
    }

    @Override
    public boolean verifyMachineIfNameAlreadyExists(String name) {
        return false;
    }

    @Override
    public Machine getMachineById(Long id) {
        return null;
    }
}
