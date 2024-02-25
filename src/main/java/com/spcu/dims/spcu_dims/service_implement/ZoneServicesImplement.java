package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Diocese;
import com.spcu.dims.spcu_dims.entities.Zone;
import com.spcu.dims.spcu_dims.repositories.DioceseRepository;
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
    private final DioceseRepository dioceseRepository;

    @Override
    public Object createZone(String name, String dioceses) {
        Optional<Zone> zoneData = zoneRepository.findByName(name);
        Optional<Diocese> dioceseData = dioceseRepository.findByName(dioceses);
        if(dioceseData.isPresent() && zoneData.isEmpty()) {
            Zone zone = new Zone();
            zone.setName(name);
            zone.setDiocese(dioceseData.get());
            return zoneRepository.save(zone);
        }
        return null;
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
        Optional<Diocese> dioceseData = dioceseRepository.findByName(diocese);
        Optional<Zone> zone = zoneRepository.findById(id);
        if(zone.isPresent() && dioceseData.isPresent()) {
            zone.get().setName(name);
            zone.get().setDiocese(dioceseData.get());
            return zoneRepository.save(zone.get());
        }
        return null;
    }

    @Override
    public DataTablesOutput<Zone> tableZone(DataTablesInput input) {
        return zoneRepository.findAll(input);
    }
}
