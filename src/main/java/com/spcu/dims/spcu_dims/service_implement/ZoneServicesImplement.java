package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.entities.Zone;
import com.spcu.dims.spcu_dims.repositories.ArchdioceseRepository;
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
    private final ArchdioceseRepository archdioceseRepository;

    @Override
    public Zone createZone(String name, String archdioceses) {
        Optional<Archdiocese> archdioceseData = archdioceseRepository.findByName(archdioceses);
        if(archdioceseData.isPresent()) {
            Zone zone = new Zone();
            zone.setName(name);
            zone.setArchdiocese(archdioceseData.get());
            return zoneRepository.save(zone);
        }
        return null;
    }

    @Override
    public List<Zone> findAll() {
        return zoneRepository.findAll();
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
    public Zone update(int id, String name, String archdiocese) {
        Optional<Archdiocese> archdioceseData = archdioceseRepository.findByName(archdiocese);
        Optional<Zone> zone = zoneRepository.findById(id);
        if(zone.isPresent() && archdioceseData.isPresent()) {
            zone.get().setName(name);
            zone.get().setArchdiocese(archdioceseData.get());
            return zoneRepository.save(zone.get());
        }
        return null;
    }

    @Override
    public DataTablesOutput<Zone> tableZone(DataTablesInput input) {
        return zoneRepository.findAll(input);
    }
}
