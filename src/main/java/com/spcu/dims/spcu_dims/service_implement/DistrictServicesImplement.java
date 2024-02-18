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
    public District createDistrict(String name, String region) {
        Optional<District> districtData = districtRepository.findByName(name);
        Optional<Region> regionData = regionRepository.findByName(region);
        if(districtData.isEmpty() && regionData.isPresent()) {
            District district = new District();
            district.setName(name);
            district.setRegion(regionData.get());
            return districtRepository.save(district);
        }
        return null;
    }

    @Override
    public List<District> findAll() {
        return districtRepository.findAll();
    }

    @Override
    public District findById(int id) {
        return districtRepository.findById(id).orElse(null);
    }

    @Override
    public District delete(int id) {
        Optional<District> district = districtRepository.findById(id);
        if(district.isPresent()) {
            district.get().setDelete(true);
            return districtRepository.save(district.get());
        }
        return null;
    }

    @Override
    public District update(int id, String name, String region) {
        Optional<Region> regionData = regionRepository.findByName(region);
        Optional<District> district = districtRepository.findById(id);
        if(district.isPresent() && regionData.isPresent()) {
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
    public List<District> findByRegionName(String region) {
        return districtRepository.findByRegionName(region);
    }


}
