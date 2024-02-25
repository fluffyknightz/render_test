package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.entities.Diocese;
import com.spcu.dims.spcu_dims.repositories.ArchdioceseRepository;
import com.spcu.dims.spcu_dims.repositories.DioceseRepository;
import com.spcu.dims.spcu_dims.services.DioceseServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DioceseServicesImplement implements DioceseServices {

    private final DioceseRepository dioceseRepository;
    private final ArchdioceseRepository archdioceseRepository;

    String existsMessage = "Diocese with the name '%s' already exists.";
    String doesNotExistMessage = "Diocese with the name '%s' does not exist.";
    String archdioceseDoesNotExistMessage = "Archdiocese with the name '%s' does not exist.";


    @Override
    public Object dioceseCreate(String name, String archdiocese) {

        Optional<Archdiocese> optionalArchdiocese = archdioceseRepository.findByName(archdiocese);

        if (optionalArchdiocese.isEmpty()) {
            return String.format(archdioceseDoesNotExistMessage, archdiocese);
        }

        Optional<Diocese> existingDioceseOptional = dioceseRepository.findByName(name);
        if (existingDioceseOptional.isPresent()) {
            if (existingDioceseOptional.get().isDelete()) {
                existingDioceseOptional.get().setDelete(false);
                existingDioceseOptional.get().setArchdiocese(optionalArchdiocese.get());
                return dioceseRepository.save(existingDioceseOptional.get());
            } else {
                return String.format(existsMessage, name);
            }
        }
        Diocese newDiocese = Diocese.builder()
                .name(name)
                .archdiocese(optionalArchdiocese.get())
                .isDelete(false)
                .build();
        return dioceseRepository.save(newDiocese);
    }


    @Override
    public List<String> findAll() {
        return dioceseRepository.findAllNotDeleted();
    }

    @Override
    public Diocese findById(int id) {
        return dioceseRepository.findById(id).orElse(null);
    }

    @Override
    public Diocese delete(int id) {
        Optional<Diocese> diocese = dioceseRepository.findById(id);
        if (diocese.isPresent()) {
            diocese.get().setDelete(true);
            return dioceseRepository.save(diocese.get());
        }
        return null;
    }

    @Override
    public Object update(int id, String name, String archdiocese) {

        Optional<Diocese> dioceseOptional = dioceseRepository.findById(id);
        Optional<Archdiocese> archdioceseExist = archdioceseRepository.findByName(archdiocese);

        if (archdioceseExist.isEmpty()) {
            return String.format(archdioceseDoesNotExistMessage, archdiocese);
        }

        if (dioceseOptional.isPresent()) {

            if (dioceseOptional.get().getName().equals(name) && dioceseOptional.get().getArchdiocese().getName().equals(archdiocese)) {
                return dioceseOptional;
            }
            // Check if the new name is already exists
            else if (dioceseRepository.findByNameAndIdNot(name, id).isPresent()) {
                return String.format(existsMessage, archdiocese);
            }

            dioceseOptional.get().setName(name);
            dioceseOptional.get().setArchdiocese(archdioceseExist.get());
            return dioceseRepository.save(dioceseOptional.get());
        }
        return String.format(doesNotExistMessage, archdiocese);

    }


    @Override
    public DataTablesOutput<Diocese> tableDiocese(DataTablesInput input) {
        return dioceseRepository.findAll(input);
    }
}
