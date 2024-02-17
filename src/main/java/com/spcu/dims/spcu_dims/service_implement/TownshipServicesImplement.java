package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.District;
import com.spcu.dims.spcu_dims.entities.Township;
import com.spcu.dims.spcu_dims.repositories.DistrictRepository;
import com.spcu.dims.spcu_dims.repositories.TownshipRepository;
import com.spcu.dims.spcu_dims.services.TownshipServices;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TownshipServicesImplement implements TownshipServices {

    private final TownshipRepository townshipRepository;
    private final DistrictRepository districtRepository;

    @Override
    public Township createTownship(String name, String district) {
        Optional<District> districtData = districtRepository.findByName(district);
        if (districtData.isPresent()) {
            Township township = new Township();
            township.setName(name);
            township.setDistrict(districtData.get());
            return townshipRepository.save(township);
        }

        return null;
    }


    @Override
    public List<Township> showAll() {
        return townshipRepository.findAll();
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
    public Township update(int id, String name, String district) {
        Optional<District> districtData = districtRepository.findByName(district);
        Optional<Township> township = townshipRepository.findById(id);
        if (township.isPresent() && districtData.isPresent()) {
            township.get().setName(name);
            township.get().setDistrict(districtData.get());
            return townshipRepository.save(township.get());
        }
        return null;
    }

    @Override
    public DataTablesOutput<Township> tableTownship(DataTablesInput input) {
        return townshipRepository.findAll(input);
    }

}