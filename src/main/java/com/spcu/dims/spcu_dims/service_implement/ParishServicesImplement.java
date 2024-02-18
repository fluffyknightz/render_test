package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Parish;
import com.spcu.dims.spcu_dims.entities.Township;
import com.spcu.dims.spcu_dims.entities.Zone;
import com.spcu.dims.spcu_dims.repositories.ParishRepository;
import com.spcu.dims.spcu_dims.repositories.TownshipRepository;
import com.spcu.dims.spcu_dims.repositories.ZoneRepository;
import com.spcu.dims.spcu_dims.services.ParishServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParishServicesImplement implements ParishServices {

    private final ParishRepository parishRepository;
    private final ZoneRepository zoneRepository;
    private final TownshipRepository townshipRepository;

    @Override
    public Parish createParish(String name, String zone, String township) {
        Optional<Zone> zoneData = zoneRepository.findByName(zone);
        Optional<Township> townshipData = townshipRepository.findByName(township);
        Optional<Parish> parishData = parishRepository.findByName(name);
        if(parishData.isEmpty() && zoneData.isPresent() && townshipData.isPresent()){
            Parish parish = new Parish();
            parish.setName(name);
            parish.setZone(zoneData.get());
            parish.setTownship(townshipData.get());
            return parishRepository.save(parish);
        }
        return null;
    }

    @Override
    public List<Parish> showAll() {
        return parishRepository.findAll();
    }

    @Override
    public Parish findById(int id) {
        return parishRepository.findById(id).orElse(null);
    }

    @Override
    public Parish delete(int id) {
        Optional<Parish> parish = parishRepository.findById(id);
        if(parish.isPresent()) {
            parish.get().setDelete(true);
            return parishRepository.save(parish.get());
        }
        return null;
    }

    @Override
    public Parish update(int id, String name, String zone, String township) {
        Optional<Zone> zoneData = zoneRepository.findByName(zone);
        Optional<Township> townshipData = townshipRepository.findByName(township);
        Optional<Parish> parish = parishRepository.findById(id);
        if(parish.isPresent() && townshipData.isPresent() && zoneData.isPresent()){
            parish.get().setName(name);
            parish.get().setZone(zoneData.get());
            parish.get().setTownship(townshipData.get());
            return parishRepository.save(parish.get());
        }
        return null;
    }

    @Override
    public DataTablesOutput<Parish> tableParish(DataTablesInput input) {
        return parishRepository.findAll(input);

    }
}
