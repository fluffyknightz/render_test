package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.District;
import com.spcu.dims.spcu_dims.entities.Region;
import com.spcu.dims.spcu_dims.repositories.DistrictRepository;
import com.spcu.dims.spcu_dims.repositories.RegionRepository;
import com.spcu.dims.spcu_dims.services.DistrictServices;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DistrictServicesImplement implements DistrictServices {

    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    @Override
    public Object createDistrict(String name, String region) {
        Optional<District> existingDistrictOptional = districtRepository.findByName(name);
        Optional<Region> optionalRegion = regionRepository.findByName(region);
        if(optionalRegion.isPresent()) {
            if (existingDistrictOptional.isPresent()) {

                if (existingDistrictOptional.get().isDelete()) {
                    existingDistrictOptional.get().setDelete(false);
                    existingDistrictOptional.get().setRegion(optionalRegion.get());
                    return districtRepository.save(existingDistrictOptional.get());
                }
                return "District with the name '" + name + "' already exists.";

            } else  {
                District newDistrict = District.builder()
                        .name(name)
                        .region(optionalRegion.get())
                        .isDelete(false)
                        .build();

                return districtRepository.save(newDistrict);
            }
        }
        return "'" + region + "' Archdiocese doesn't exist!";

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
    public District update(int id, String name, String region) {
        Optional<Region> regionData = regionRepository.findByName(region);
        Optional<District> district = districtRepository.findById(id);
        if (district.isPresent() && regionData.isPresent()) {
            district.get().setName(name);
            district.get().setRegion(regionData.get());
            return districtRepository.save(district.get());
        }
        return null;
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
