package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.District;
import com.spcu.dims.spcu_dims.entities.Township;
import com.spcu.dims.spcu_dims.entities.Zone;
import com.spcu.dims.spcu_dims.repositories.DistrictRepository;
import com.spcu.dims.spcu_dims.repositories.TownshipRepository;
import com.spcu.dims.spcu_dims.repositories.ZoneRepository;
import com.spcu.dims.spcu_dims.services.TownshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TownshipServiceImplement implements TownshipService {

    private final TownshipRepository townshipRepository;
    private final DistrictRepository districtRepository;
    private final ZoneRepository zoneRepository;

    String existsMessage = "Township with the name '%s' already exists.";
    String doesNotExistMessage = "Township with the name '%s' does not exist.";
    String districtDoesNotExistMessage = "District with the name '%s' does not exist.";
    String zoneDoesNotExistMessage = "Zone with the name '%s' does not exist.";
    String bothDoesNotExistMessage = "Both District with the name '%s' and Zone with the name '%s' do not exist.";

    @Override
    public Object createTownship(String name, String district, String zone) {
        Optional<District> optionalDistrict = districtRepository.findByName(district);
        Optional<Zone> optionalZone = zoneRepository.findByName(zone);
        if (optionalZone.isEmpty() && optionalDistrict.isEmpty()) {
            return String.format(bothDoesNotExistMessage, district, zone);
        } else if (optionalDistrict.isEmpty()) {
            return String.format(districtDoesNotExistMessage, district);
        } else if (optionalZone.isEmpty()) {
            return String.format(zoneDoesNotExistMessage, zone);
        }

        Optional<Township> existingTownshipOptional = townshipRepository.findByName(name);
        if (existingTownshipOptional.isPresent()) {
            if (existingTownshipOptional.get().isDelete()) {
                existingTownshipOptional.get().setDelete(false);
                existingTownshipOptional.get().setDistrict(optionalDistrict.get());
                existingTownshipOptional.get().setZone(optionalZone.get());
                return townshipRepository.save(existingTownshipOptional.get());
            } else {
                return String.format(existsMessage, name);
            }
        }
        Township newTownship = Township.builder().name(name).district(optionalDistrict.get()).zone(optionalZone.get()).build();
        return townshipRepository.save(newTownship);
    }


    @Override
    public List<String> showAll() {
        return townshipRepository.findAllNotDeleted();
    }

    @Override
    public Township findById(int id) {
        return townshipRepository.findById(id).orElse(null);
    }

    @Override
    public Township delete(int id) {
        Optional<Township> township = townshipRepository.findById(id);
        if (township.isPresent()) {
            township.get().setDelete(true);
            return townshipRepository.save(township.get());
        }
        return null;
    }

    @Override
    public Object update(int id, String name, String district, String zone) {
        Optional<Township> townshipOptional = townshipRepository.findById(id);
        Optional<District> districtExist = districtRepository.findByName(district);
        Optional<Zone> zoneExist = zoneRepository.findByName(zone);

        if (districtExist.isEmpty() && zoneExist.isEmpty()) {
            return String.format(bothDoesNotExistMessage, district, zone);
        } else if (districtExist.isEmpty()) {
            return String.format(districtDoesNotExistMessage, district);
        } else if (zoneExist.isEmpty()) {
            return String.format(zoneDoesNotExistMessage, zone);
        }

        if (townshipOptional.isPresent()) {

            if (townshipOptional.get().getName().equals(name) && townshipOptional.get().getDistrict().getName().equals(district) && townshipOptional.get().getZone().getName().equals(zone)) {
                return townshipOptional;
            }
            // Check if the new name is already exists
            else if (districtRepository.findByNameAndIdNot(name, id).isPresent()) {
                return String.format(existsMessage, name);
            }

            townshipOptional.get().setName(name);
            townshipOptional.get().setDistrict(districtExist.get());
            townshipOptional.get().setZone(zoneExist.get());
            return townshipRepository.save(townshipOptional.get());
        }
        return String.format(doesNotExistMessage, name);

    }

    @Override
    public DataTablesOutput<Township> tableTownship(DataTablesInput input) {
        return townshipRepository.findAll(input);
    }

}