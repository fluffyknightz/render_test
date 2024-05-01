package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Region;
import com.spcu.dims.spcu_dims.repositories.RegionRepository;
import com.spcu.dims.spcu_dims.services.RegionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionServicesImplement implements RegionServices {

    private final RegionRepository regionRepository;
    String existsMessage = "Region with the name '%s' already exists.";
    String doesNotExistMessage = "Region with the name '%s' does not exist.";

    @Override
    public Object createRegion(String name) {

        Optional<Region> optionalRegion = regionRepository.findByName(name);

        if (optionalRegion.isPresent()) {

            if (optionalRegion.get().isDelete()) {
                optionalRegion.get().setDelete(false);
                return regionRepository.save(optionalRegion.get());
            }
            return String.format(existsMessage, name);

        }

        Region newRegion = Region.builder().name(name).build();

        return regionRepository.save(newRegion);
    }

    @Override
    public List<String> findAll() {
        return regionRepository.findAllNotDeleted();
    }

    @Override
    public Region findById(int id) {
        return regionRepository.findById(id).orElse(null);
    }

    @Override
    public Region delete(int id) {
        Optional<Region> region = regionRepository.findById(id);
        if (region.isPresent()) {
            region.get().setDelete(true);
            return regionRepository.save(region.get());
        }
        return null;
    }

    @Override
    public Object update(int id, String name) {
        Optional<Region> region = regionRepository.findById(id);
        Optional<Region> existRegion = regionRepository.findByNameAndIdNot(name, id);

        if (existRegion.isEmpty()) {
            if (region.isPresent()) {

                if (region.get().getName().equals(name)) {
                    return region;
                }

                region.get().setName(name);
                return regionRepository.save(region.get());
            }
        } else {
            return String.format(existsMessage, name);

        }
        return String.format(doesNotExistMessage, name);
    }

    @Override
    public DataTablesOutput<Region> tableRegion(DataTablesInput input) {
        return regionRepository.findAll(input);
    }
}
