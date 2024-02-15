package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.repositories.ArchdioceseRepository;
import com.spcu.dims.spcu_dims.repositories.DioceseRepository;
import com.spcu.dims.spcu_dims.services.ArchdioceseServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArchdioceseServicesImplement implements ArchdioceseServices {

    private final ArchdioceseRepository archdioceseRepository;
    private final DioceseRepository dioceseRepository;


    @Override
    public Archdiocese createArchdiocese(String name, int dioceseId) {
        return archdioceseRepository.save(Archdiocese.builder().name(name).isDelete(false).diocese(dioceseRepository.findById(dioceseId).orElse(null)).build());
    }

    @Override
    public List<Archdiocese> showAll() {
        return archdioceseRepository.findAll();
    }

    @Override
    public Archdiocese findById(int id) {
        return archdioceseRepository.findById(id).orElse(null);
    }

    @Override
    public Archdiocese delete(int id) {
        Optional<Archdiocese> archdiocese = archdioceseRepository.findById(id);
        if(archdiocese.isPresent()) {
            archdiocese.get().setDelete(true);
            return archdioceseRepository.save(archdiocese.get());
        }
        return null;
    }

    @Override
    public Archdiocese update(int id, String name) {
        Optional<Archdiocese> archdiocese = archdioceseRepository.findById(id);
        if(archdiocese.isPresent()) {
            archdiocese.get().setName(name);
            return archdioceseRepository.save(archdiocese.get());
        }
        return null;
    }
}
