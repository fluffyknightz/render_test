package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Diocese;
import com.spcu.dims.spcu_dims.entities.District;
import com.spcu.dims.spcu_dims.repositories.DistrictRepository;
import com.spcu.dims.spcu_dims.repositories.RegionRepository;
import com.spcu.dims.spcu_dims.services.DioceseServices;
import com.spcu.dims.spcu_dims.services.DistrictServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DistrictServicesImplement implements DistrictServices {

    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;
    @Override
    public District createDistrict(String name, int regionId) {
        return districtRepository.save(District.builder().name(name).isDelete(false).region(regionRepository.findById(regionId).orElse(null)).build());
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
    public District update(int id, String name) {
        Optional<District> district = districtRepository.findById(id);
        if(district.isPresent()) {
            district.get().setName(name);
            return districtRepository.save(district.get());
        }
        return null;
    }
}
