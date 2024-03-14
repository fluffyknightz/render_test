package com.spcu.dims.spcu_dims.service_implement;


import com.spcu.dims.spcu_dims.entities.Diocese;
import com.spcu.dims.spcu_dims.entities.Zone;
import com.spcu.dims.spcu_dims.repositories.DioceseRepository;
import com.spcu.dims.spcu_dims.repositories.ZoneRepository;
import com.spcu.dims.spcu_dims.services.ZoneServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZoneServicesImplement implements ZoneServices {

    private final ZoneRepository zoneRepository;
    private final DioceseRepository dioceseRepository;

    String existsMessage = "Zone with the name '%s' already exists.";
    String doesNotExistMessage = "Zone with the name '%s' does not exist.";
    String dioceseDoesNotExistMessage = "Diocese with the name '%s' does not exist.";

    @Override
    public Object createZone(String name, String diocese) {
        Optional<Diocese> optionalDiocese = dioceseRepository.findByName(diocese);

        if (optionalDiocese.isEmpty()) {
            return String.format(dioceseDoesNotExistMessage, diocese);
        }

        Optional<Zone> existingZoneOptional = zoneRepository.findByName(name);
        if (existingZoneOptional.isPresent()) {
            if (existingZoneOptional.get().isDelete()) {
                existingZoneOptional.get().setDelete(false);
                existingZoneOptional.get().setDiocese(optionalDiocese.get());
                return zoneRepository.save(existingZoneOptional.get());
            } else {
                return String.format(existsMessage, name);
            }
        }
        Zone newZone = Zone.builder()
                .name(name)
                .diocese(optionalDiocese.get())
                .build();
        return zoneRepository.save(newZone);
    }

    @Override
    public List<String> findAll() {
        return zoneRepository.findAllNotDeleted();
    }

    @Override
    public Zone findById(int id) {
        return zoneRepository.findById(id).orElse(null);
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
    public Object update(int id, String name, String diocese) {
        Optional<Zone> zoneOptional = zoneRepository.findById(id);
        Optional<Diocese> dioceseExist = dioceseRepository.findByName(diocese);

        if (dioceseExist.isEmpty()) {
            return String.format(dioceseDoesNotExistMessage, diocese);
        }

        if (zoneOptional.isPresent()) {

            if (zoneOptional.get().getName().equals(name) && zoneOptional.get().getDiocese().getName().equals(diocese)) {
                return zoneOptional;
            }
            // Check if the new name is already exists
            else if (dioceseRepository.findByNameAndIdNot(name, id).isPresent()) {
                return String.format(existsMessage, name);
            }

            zoneOptional.get().setName(name);
            zoneOptional.get().setDiocese(dioceseExist.get());
            return zoneRepository.save(zoneOptional.get());
        }
        return String.format(doesNotExistMessage, name);
    }

    @Override
    public DataTablesOutput<Zone> tableZone(DataTablesInput input) {
        return zoneRepository.findAll(input);
    }

    @Override
    public List<String> findByDioceseName(String name) {
        return zoneRepository.findByDioceseName(name);

    }
}
