package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.District;
import com.spcu.dims.spcu_dims.entities.Region;
import com.spcu.dims.spcu_dims.repositories.DistrictRepository;
import com.spcu.dims.spcu_dims.repositories.RegionRepository;
import com.spcu.dims.spcu_dims.services.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictServiceImplement implements DistrictService {

    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    String existsMessage = "District with the name '%s' already exists.";
    String doesNotExistMessage = "District with the name '%s' does not exist.";
    String regionDoesNotExistMessage = "Region with the name '%s' does not exist.";

    @Override
    public Object createDistrict(String name, String region) {
        Optional<Region> optionalRegion = regionRepository.findByName(region);

        if (optionalRegion.isEmpty()) {
            return String.format(regionDoesNotExistMessage, region);
        }

        Optional<District> existingDistrictOptional = districtRepository.findByName(name);
        if (existingDistrictOptional.isPresent()) {
            if (existingDistrictOptional.get().isDelete()) {
                existingDistrictOptional.get().setDelete(false);
                existingDistrictOptional.get().setRegion(optionalRegion.get());
                return districtRepository.save(existingDistrictOptional.get());
            } else {
                return String.format(existsMessage, name);
            }
        }
        District newDistrict = District.builder()
                .name(name)
                .region(optionalRegion.get())
                .build();
        return districtRepository.save(newDistrict);
    }

    @Override
    public List<String> findAll() {
        return districtRepository.findAllNotDeleted();
    }

    @Override
    public District findById(int id) {
        return districtRepository.findById(id).orElse(null);
    }

    @Override
    public District delete(int id) {
        Optional<District> district = districtRepository.findById(id);
        if (district.isPresent()) {
            district.get().setDelete(true);
            return districtRepository.save(district.get());
        }
        return null;
    }

    @Override
    public Object update(int id, String name, String region) {
        Optional<District> districtOptional = districtRepository.findById(id);
        Optional<Region> regionExist = regionRepository.findByName(region);

        if (regionExist.isEmpty()) {
            return String.format(regionDoesNotExistMessage, region);
        }

        if (districtOptional.isPresent()) {

            if (districtOptional.get().getName().equals(name) && districtOptional.get().getRegion().getName().equals(region)) {
                return districtOptional;
            }
            // Check if the new name is already exists
            else if (districtRepository.findByNameAndIdNot(name, id).isPresent()) {
                return String.format(existsMessage, name);
            }

            districtOptional.get().setName(name);
            districtOptional.get().setRegion(regionExist.get());
            return districtRepository.save(districtOptional.get());
        }
        return String.format(doesNotExistMessage, name);

    }

    @Override
    public DataTablesOutput<District> tableDistrict(DataTablesInput input) {
        return districtRepository.findAll(input);
    }

    @Override
    public List<String> findByRegionName(String region) {
        return districtRepository.findByRegionName(region);
    }


}
