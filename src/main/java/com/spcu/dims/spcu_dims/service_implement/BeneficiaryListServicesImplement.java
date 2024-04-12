package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.dto.BeneficiaryListDto;
import com.spcu.dims.spcu_dims.entities.*;
import com.spcu.dims.spcu_dims.repositories.*;
import com.spcu.dims.spcu_dims.services.BeneficiaryListServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeneficiaryListServicesImplement implements BeneficiaryListServices {

    private final BeneficiaryListViewRepository beneficiaryListViewRepository;
    private final BeneficiaryListRepository beneficiaryListRepository;
    private final ActivityRepository activityRepository;
    private final ParishRepository parishRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    private final DisabilityRepository disabilityRepository;
    private final VulnerabilityRepository vulnerabilityRepository;
    private final UserRepository userRepository;

    String doesNotExistMessage = "BeneficiaryList does not exist.";
    String activityDoesNotExistMessage = "Activity with the name '%s' does not exist.";
    String userDoesNotExistMessage = "User does not exist.";
    String parishDoesNotExistMessage = "Parish with the name '%s' does not exist.";
    String bothDoesNotExistMessage = "Activity with the name '%s' and Parish with the name '%s' do not exist.";


    @Override
    public Object createBeneficiaryList(BeneficiaryListDto reqData, int userId) {

        Optional<Activity> activityExist = activityRepository.findByName(reqData.getActivityName());
        Optional<Parish> parishExist = parishRepository.findByName(reqData.getParishName());

        if (activityExist.isEmpty() && parishExist.isEmpty()) {
            return String.format(bothDoesNotExistMessage, reqData.getActivityName(), reqData.getParishName());
        } else if (activityExist.isEmpty()) {
            return String.format(activityDoesNotExistMessage, reqData.getActivityName());
        } else if (parishExist.isEmpty()) {
            return String.format(parishDoesNotExistMessage, reqData.getParishName());
        }

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return String.format(doesNotExistMessage);
        }

        Beneficiary beneficiary = Beneficiary.builder().household(reqData.getBeneficiaryHousehold()).male(reqData.getBeneficiaryMale()).female(reqData.getBeneficiaryFemale()).build();
        beneficiaryRepository.save(beneficiary);

        Disability disability = Disability.builder().male(reqData.getDisabilityMale()).female(reqData.getBeneficiaryFemale()).build();
        disabilityRepository.save(disability);

        Vulnerability vulnerability = Vulnerability.builder().under18Female(reqData.getVulnerabilityUnder18Female()).under18Male(reqData.getVulnerabilityUnder18Male()).over60Female(reqData.getVulnerabilityOver60Female()).over60Male(reqData.getVulnerabilityOver60Male()).build();
        vulnerabilityRepository.save(vulnerability);

        BeneficiaryList beneficiaryList = BeneficiaryList.builder().user(user.get()).createdDate(reqData.getCreatedDate()).activity(activityExist.get()).parish(parishExist.get()).beneficiary(beneficiary).disability(disability).vulnerability(vulnerability).build();
        return beneficiaryListRepository.save(beneficiaryList);
    }

    @Override
    public List<BeneficiaryList> showAll() {
        return beneficiaryListRepository.findAll();
    }

    @Override
    public BeneficiaryList findById(int id) {
        return beneficiaryListRepository.findById(id).orElse(null);
    }

    @Override
    public BeneficiaryList delete(int id) {
        Optional<BeneficiaryList> beneficiaryList = beneficiaryListRepository.findById(id);
        if (beneficiaryList.isPresent()) {
            beneficiaryList.get().setDelete(true);
            return beneficiaryListRepository.save(beneficiaryList.get());
        }
        return null;
    }

    @Override
    public Object update(BeneficiaryListDto reqData) {

        Optional<BeneficiaryList> beneficiaryListOptional = beneficiaryListRepository.findById(reqData.getId());

        Optional<Activity> activityExist = activityRepository.findByName(reqData.getActivityName());
        Optional<Parish> parishExist = parishRepository.findByName(reqData.getParishName());

        if (activityExist.isEmpty() && parishExist.isEmpty()) {
            return String.format(bothDoesNotExistMessage, reqData.getActivityName(), reqData.getParishName());
        } else if (activityExist.isEmpty()) {
            return String.format(activityDoesNotExistMessage, reqData.getActivityName());
        } else if (parishExist.isEmpty()) {
            return String.format(parishDoesNotExistMessage, reqData.getParishName());
        }

        if (beneficiaryListOptional.isPresent()) {

            Optional<Beneficiary> beneficiaryExist = beneficiaryRepository.findById(beneficiaryListOptional.get().getBeneficiary().getId());
            Optional<Disability> disabilityExist = disabilityRepository.findById(beneficiaryListOptional.get().getDisability().getId());
            Optional<Vulnerability> vulnerabilityExist = vulnerabilityRepository.findById(beneficiaryListOptional.get().getVulnerability().getId());

            if (beneficiaryExist.isPresent() && disabilityExist.isPresent() && vulnerabilityExist.isPresent()) {

                beneficiaryExist.get().setHousehold(reqData.getBeneficiaryHousehold());
                beneficiaryExist.get().setMale(reqData.getBeneficiaryMale());
                beneficiaryExist.get().setFemale(reqData.getBeneficiaryFemale());

                disabilityExist.get().setMale(reqData.getDisabilityMale());
                disabilityExist.get().setFemale(reqData.getDisabilityFemale());

                vulnerabilityExist.get().setUnder18Male(reqData.getVulnerabilityUnder18Male());
                vulnerabilityExist.get().setUnder18Female(reqData.getVulnerabilityUnder18Female());
                vulnerabilityExist.get().setOver60Male(reqData.getVulnerabilityOver60Male());
                vulnerabilityExist.get().setOver60Female(reqData.getVulnerabilityOver60Female());

                beneficiaryListOptional.get().setActivity(activityExist.get());
                beneficiaryListOptional.get().setParish(parishExist.get());
                beneficiaryListOptional.get().setBeneficiary(beneficiaryExist.get());
                beneficiaryListOptional.get().setDisability(disabilityExist.get());
                beneficiaryListOptional.get().setVulnerability(vulnerabilityExist.get());

                return beneficiaryListRepository.save(beneficiaryListOptional.get());
            }
        }
        return String.format(doesNotExistMessage);
    }

    @Override
    public DataTablesOutput<BeneficiaryListView> tableBeneficiaryList(DataTablesInput input) {
        return beneficiaryListViewRepository.findAll(input);
    }

}
