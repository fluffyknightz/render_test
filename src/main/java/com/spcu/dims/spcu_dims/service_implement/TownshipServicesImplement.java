package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Region;
import com.spcu.dims.spcu_dims.entities.Township;
import com.spcu.dims.spcu_dims.repositories.DistrictRepository;
import com.spcu.dims.spcu_dims.repositories.TownshipRepository;
import com.spcu.dims.spcu_dims.services.TownshipServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TownshipServicesImplement implements TownshipServices {

    private final TownshipRepository townshipRepository;
    private final DistrictRepository districtRepository;
    @Override
    public Township createTownship(String name, int districtId) {
        return townshipRepository.save(Township.builder().name(name).isDelete(false).district(districtRepository.findById(districtId).orElse(null)).build());
    }

    @Override
    public List<Township> findAll() {
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
    public Township update(int id, String name) {
        Optional<Township> township = townshipRepository.findById(id);
        if (township.isPresent()) {
            township.get().setName(name);
            return townshipRepository.save(township.get());
        }
        return null;
    }
}
