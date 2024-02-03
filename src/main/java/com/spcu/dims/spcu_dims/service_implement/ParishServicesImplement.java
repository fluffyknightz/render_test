package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Parish;
import com.spcu.dims.spcu_dims.repositories.ArchdioceseRepository;
import com.spcu.dims.spcu_dims.repositories.ParishRepository;
import com.spcu.dims.spcu_dims.services.ParishServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParishServicesImplement implements ParishServices {

    private final ParishRepository parishRepository;
    private final ArchdioceseRepository archdioceseRepository;

    @Override
    public Parish createParish(String name, int archdioceseId) {
        return parishRepository.save(Parish.builder().name(name).isDelete(false).archdiocese(archdioceseRepository.findById(archdioceseId).orElse(null)).build());
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
        if (parish.isPresent()) {
            parish.get().setDelete(true);
            return parishRepository.save(parish.get());
        }
        return null;
    }

    @Override
    public Parish update(int id, String name) {

        Optional<Parish> parish = parishRepository.findById(id);
        if (parish.isPresent()) {
            parish.get().setName(name);
            return parishRepository.save(parish.get());
        }
        return null;
    }
}
