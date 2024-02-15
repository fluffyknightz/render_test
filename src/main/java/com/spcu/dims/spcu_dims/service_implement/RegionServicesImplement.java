package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Region;
import com.spcu.dims.spcu_dims.repositories.RegionRepository;
import com.spcu.dims.spcu_dims.services.RegionServices;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegionServicesImplement implements RegionServices {

    private final RegionRepository regionRepository;
    @Override
    public Region createRegion(String name) {

        return regionRepository.save(Region.builder().name(name).isDelete(false).build());
    }

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region findById(int id) {
        return regionRepository.findById(id).orElse(null);
    }

    @Override
    public Region delete(int id) {
        Optional<Region> region = regionRepository.findById(id);
        if(region.isPresent()) {
            region.get().setDelete(true);
            return regionRepository.save(region.get());
        }
        return null;
    }

    @Override
    public Region update(int id, String name) {
        Optional<Region> region = regionRepository.findById(id);
        if(region.isPresent()) {
            region.get().setName(name);
            return regionRepository.save(region.get());
        }
        return null;
    }

    @Override
    public DataTablesOutput<Region> tableRegion(DataTablesInput input) {
        return regionRepository.findAll(input);
    }
}
