package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.entities.Diocese;
import com.spcu.dims.spcu_dims.repositories.ArchdioceseRepository;
import com.spcu.dims.spcu_dims.repositories.DioceseRepository;
import com.spcu.dims.spcu_dims.services.ArchdioceseServices;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArchdioceseServicesImplement implements ArchdioceseServices {

    private final ArchdioceseRepository archdioceseRepository;
    private final DioceseRepository dioceseRepository;


    @Override
    public Archdiocese createArchdiocese(String name, String diocese) {
        Optional<Diocese> dioceseData = dioceseRepository.findByName(diocese);
        Optional<Archdiocese> archdioceseData = archdioceseRepository.findByName(name);
        if(dioceseData.isPresent() && archdioceseData.isEmpty()) {
            Archdiocese archdiocese = new Archdiocese();
            archdiocese.setName(name);
            archdiocese.setDiocese(dioceseData.get());
            return archdioceseRepository.save(archdiocese);
        }

        return null;
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
    public Archdiocese update(int id, String name, String diocese) {
        Optional<Diocese> dioceseData = dioceseRepository.findByName(diocese);
        Optional<Archdiocese> archdiocese = archdioceseRepository.findById(id);
        if(archdiocese.isPresent() && dioceseData.isPresent()) {
            archdiocese.get().setName(name);
            archdiocese.get().setDiocese(dioceseData.get());
            return archdioceseRepository.save(archdiocese.get());
        }
        return null;
    }

    @Override
    public DataTablesOutput<Archdiocese> tableArchdiocese(DataTablesInput input) {
        return archdioceseRepository.findAll(input);
    }
}
