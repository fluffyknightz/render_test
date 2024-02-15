package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Diocese;
import com.spcu.dims.spcu_dims.repositories.DioceseRepository;
import com.spcu.dims.spcu_dims.services.DioceseServices;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DioceseServicesImplement implements DioceseServices {

    private final DioceseRepository dioceseRepository;

    @Override
    public Diocese dioceseCreate(String name) {

        if (!dioceseRepository.existsByName(name)) {
            return dioceseRepository.save(Diocese.builder().name(name).isDelete(false).build());
        } else {
            Optional<Diocese> existingDioceseOptional = dioceseRepository.findByName(name);

            if (existingDioceseOptional.isPresent()) {
                Diocese existingDiocese = existingDioceseOptional.get();

                if (existingDiocese.isDelete()) {
                    existingDiocese.setDelete(false);
                    return dioceseRepository.save(existingDiocese);
                }

            }
        }
        return null;
    }

    @Override
    public List<Diocese> findAll() {
        return dioceseRepository.findAll();
    }

    @Override
    public Diocese findById(int id) {
        return dioceseRepository.findById(id).orElse(null);
    }

    @Override
    public Diocese delete(int id) {
        Optional<Diocese> diocese = dioceseRepository.findById(id);
        if(diocese.isPresent()) {
            diocese.get().setDelete(true);
            return dioceseRepository.save(diocese.get());
        }
       return null;
    }

    @Override
    public Diocese update(int id, String name) {
        Optional<Diocese> diocese = dioceseRepository.findById(id);
        boolean exist = dioceseRepository.existsByName(name);
        if(diocese.isPresent() && !diocese.get().getName().equals(name) && !exist) {
            diocese.get().setName(name);
            return dioceseRepository.save(diocese.get());
        }
        return null;
    }

    @Override
    public DataTablesOutput<Diocese> tableDiocese(DataTablesInput input) {
        return dioceseRepository.findAll(input);
    }
}
