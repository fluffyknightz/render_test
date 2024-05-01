package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Congregation;
import com.spcu.dims.spcu_dims.entities.Parish;
import com.spcu.dims.spcu_dims.entities.Township;
import com.spcu.dims.spcu_dims.repositories.CongregationRepository;
import com.spcu.dims.spcu_dims.repositories.ParishRepository;
import com.spcu.dims.spcu_dims.repositories.TownshipRepository;
import com.spcu.dims.spcu_dims.services.ParishServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParishServicesImplement implements ParishServices {

    private final ParishRepository parishRepository;
    private final TownshipRepository townshipRepository;
    private final CongregationRepository congregationRepository;

    String existsMessage = "Parish with the name '%s' already exists.";
    String doesNotExistMessage = "Parish with the name '%s' does not exist.";
    String townshipDoesNotExistMessage = "Township with the name '%s' does not exist.";
    String congregationNotExistMessage = "Congregation with the name '%s' does not exist.";
    String bothNotExistMessage = "Both Township with the name '%s' and Congregation with the name '%s' do not exist.";

    @Override
    public Object createParish(String name, String township, String congregation, String note) {

        Optional<Township> townshipExist = townshipRepository.findByName(township);
        Optional<Congregation> congregationExist = congregationRepository.findByName(congregation);

        if (townshipExist.isEmpty() && congregationExist.isEmpty()) {
            return String.format(bothNotExistMessage, township, congregation);
        } else if (townshipExist.isEmpty()) {
            return String.format(townshipDoesNotExistMessage, township);
        } else if (congregationExist.isEmpty()) {
            return String.format(congregationNotExistMessage, congregation);
        }

        Optional<Parish> existingParishOptional = parishRepository.findByName(name);
        if (existingParishOptional.isPresent()) {
            if (existingParishOptional.get().isDelete()) {
                existingParishOptional.get().setDelete(false);
                existingParishOptional.get().setTownship(townshipExist.get());
                existingParishOptional.get().setCongregation(congregationExist.get());
                existingParishOptional.get().setNote(note);
                return parishRepository.save(existingParishOptional.get());
            } else {
                return String.format(existsMessage, name);
            }
        }
        Parish newParish = Parish.builder().name(name).township(townshipExist.get()).congregation(congregationExist.get()).note(note).build();
        return parishRepository.save(newParish);
    }

    @Override
    public List<String> showAll() {
        return parishRepository.findAllNotDeleted();
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
    public Object update(int id, String name, String township, String congregation, String note) {
        Optional<Parish> parishOptional = parishRepository.findById(id);
        Optional<Township> townshipExist = townshipRepository.findByName(township);
        Optional<Congregation> congregationExist = congregationRepository.findByName(congregation);

        if (townshipExist.isEmpty() && congregationExist.isEmpty()) {
            return String.format(bothNotExistMessage, township, congregation);
        } else if (townshipExist.isEmpty()) {
            return String.format(townshipDoesNotExistMessage, township);
        } else if (congregationExist.isEmpty()) {
            return String.format(congregationNotExistMessage, congregation);
        }

        if (parishOptional.isPresent()) {

            if (parishOptional.get().getName().equals(name) && parishOptional.get().getTownship().getName().equals(township) && congregationExist.get().getName().equals(congregation) && parishOptional.get().getNote().equals(note)) {
                return parishOptional;
            }
            // Check if the new name is already exists
            else if (parishRepository.findByNameAndIdNot(name, id).isPresent()) {
                return String.format(existsMessage, name);
            }

            parishOptional.get().setName(name);
            parishOptional.get().setTownship(townshipExist.get());
            parishOptional.get().setCongregation(congregationExist.get());
            parishOptional.get().setNote(note);
            return parishRepository.save(parishOptional.get());
        }
        return String.format(doesNotExistMessage, name);
    }

    @Override
    public DataTablesOutput<Parish> tableParish(DataTablesInput input) {
        return parishRepository.findAll(input);

    }

    @Override
    public List<String> findByZoneName(String zone) {
        return parishRepository.findByZoneName(zone);
    }

    @Override
    public int parishCount() {
        return parishRepository.parishCount();
    }
}
