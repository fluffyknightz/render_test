package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Congregation;
import com.spcu.dims.spcu_dims.repositories.CongregationRepository;
import com.spcu.dims.spcu_dims.services.CongregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CongregationServiceImplement implements CongregationService {

    private final CongregationRepository congregationRepository;

    String existsMessage = "Congregation with the name '%s' already exists.";
    String doesNotExistMessage = "Congregation with the name '%s' does not exist.";

    @Override
    public Object createCongregation(String name) {

        Optional<Congregation> optionalCongregation = congregationRepository.findByName(name);

        if (optionalCongregation.isPresent()) {

            if (optionalCongregation.get().isDelete()) {
                optionalCongregation.get().setDelete(false);
                return congregationRepository.save(optionalCongregation.get());
            }
            return String.format(existsMessage, name);

        }

        Congregation newCongregation = Congregation.builder().name(name).build();

        return congregationRepository.save(newCongregation);
    }

    @Override
    public List<String> showAll() {
        return congregationRepository.findAllNotDeleted();
    }

    @Override
    public Congregation findById(int id) {
        return congregationRepository.findById(id).orElse(null);
    }

    @Override
    public Congregation delete(int id) {
        Optional<Congregation> congregation = congregationRepository.findById(id);
        if (congregation.isPresent()) {
            congregation.get().setDelete(true);
            return congregationRepository.save(congregation.get());
        }
        return null;
    }

    @Override
    public Object update(int id, String name) {
        Optional<Congregation> congregation = congregationRepository.findById(id);
        Optional<Congregation> existCongregation = congregationRepository.findByNameAndIdNot(name, id);
        if (existCongregation.isPresent()) {
            return String.format(existsMessage, name);
        } else if (congregation.isPresent()) {

            if (congregation.get().getName().equals(name)) {
                return congregation;
            }

            congregation.get().setName(name);
            return congregationRepository.save(congregation.get());
        }
        return String.format(doesNotExistMessage, name);
    }

    @Override
    public DataTablesOutput<Congregation> congregationTable(DataTablesInput input) {
        return congregationRepository.findAll(input);
    }

}
