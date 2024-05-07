package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.repositories.ArchdioceseRepository;
import com.spcu.dims.spcu_dims.services.ArchdioceseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArchdioceseServiceImplement implements ArchdioceseService {

    private final ArchdioceseRepository archdioceseRepository;

    String existsMessage = "Archdiocese with the name '%s' already exists.";
    String doesNotExistMessage = "Archdiocese with the name '%s' does not exist.";

    @Override
    public Object createArchdiocese(String name) {

        Optional<Archdiocese> optionalArchdiocese = archdioceseRepository.findByName(name);

        if (optionalArchdiocese.isPresent()) {

            if (optionalArchdiocese.get().isDelete()) {
                optionalArchdiocese.get().setDelete(false);
                return archdioceseRepository.save(optionalArchdiocese.get());
            }
            return String.format(existsMessage, name);

        }

        Archdiocese newArchdiocese = Archdiocese.builder().name(name).build();

        return archdioceseRepository.save(newArchdiocese);
    }

    @Override
    public List<String> showAll() {
        return archdioceseRepository.findAllNotDeleted();
    }

    @Override
    public Archdiocese findById(int id) {
        return archdioceseRepository.findById(id).orElse(null);
    }

    @Override
    public Archdiocese delete(int id) {
        Optional<Archdiocese> archdiocese = archdioceseRepository.findById(id);
        if (archdiocese.isPresent()) {
            archdiocese.get().setDelete(true);
            return archdioceseRepository.save(archdiocese.get());
        }
        return null;
    }

    @Override
    public Object update(int id, String name) {
        Optional<Archdiocese> archdiocese = archdioceseRepository.findById(id);
        Optional<Archdiocese> existArchdiocese = archdioceseRepository.findByNameAndIdNot(name, id);
        if (existArchdiocese.isPresent()) {
            return String.format(existsMessage, name);
        } else if (archdiocese.isPresent()) {

            if (archdiocese.get().getName().equals(name)) {
                return archdiocese;
            }

            archdiocese.get().setName(name);
            return archdioceseRepository.save(archdiocese.get());
        }
        return String.format(doesNotExistMessage, name);
    }

    @Override
    public DataTablesOutput<Archdiocese> tableArchdiocese(DataTablesInput input) {
        return archdioceseRepository.findAll(input);
    }
}
