package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Zone;
import com.spcu.dims.spcu_dims.repositories.ZoneRepository;
import com.spcu.dims.spcu_dims.services.ZoneServices;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ZoneServicesImplement implements ZoneServices {

    private final ZoneRepository zoneRepository;
    @Override
    public Zone zoneCreate(String name) {
        if (!zoneRepository.existsByName(name)) {
            return zoneRepository.save(Zone.builder().name(name).isDelete(false).build());
        } else {
            Optional<Zone> existingZoneOptional = zoneRepository.findByName(name);

            if (existingZoneOptional.isPresent()) {
                Zone existingZone = existingZoneOptional.get();

                if (existingZone.isDelete()) {
                    existingZone.setDelete(false);
                    return zoneRepository.save(existingZone);
                }
            }
        }
        return null;
    }

    @Override
    public List<Zone> findAll() {
        return zoneRepository.findAll();
    }

    @Override
    public Zone findById(int id) {
        return null;
    }

    @Override
    public Zone delete(int id) {
        Optional<Zone> zone = zoneRepository.findById(id);
        if(zone.isPresent()) {
            zone.get().setDelete(true);
            return zoneRepository.save(zone.get());
        }
        return null;
    }

    @Override
    public Zone update(int id, String name) {
        return null;
    }

    @Override
    public DataTablesOutput<Zone> tableZone(DataTablesInput input) {
        return zoneRepository.findAll(input);
    }
}
