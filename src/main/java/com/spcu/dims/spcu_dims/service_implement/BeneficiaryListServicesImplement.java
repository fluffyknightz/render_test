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

    private final BeneficiaryListRepository beneficiaryListRepository;
    private final ActivityRepository activityRepository;
    private final ParishRepository parishRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    private final DisabilityRepository disabilityRepository;
    private final VulnerabilityRepository vulnerabilityRepository;

    String doesNotExistMessage = "BeneficiaryList does not exist.";
    String activityDoesNotExistMessage = "Activity with the name '%s' does not exist.";
    String parishDoesNotExistMessage = "Parish with the name '%s' does not exist.";
    String bothDoesNotExistMessage = "Activity with the name '%s' and Parish with the name '%s' do not exist.";



    @Override
    public Object createBeneficiaryList(BeneficiaryListDto reqData) {

        Optional<Activity> activityExist = activityRepository.findByName(reqData.getActivityName());
        Optional<Parish> parishExist = parishRepository.findByName(reqData.getParishName());

        if (activityExist.isEmpty() && parishExist.isEmpty()){
            return String.format(bothDoesNotExistMessage, reqData.getActivityName(), reqData.getParishName());
        }
        else if(activityExist.isEmpty()){
            return String.format(activityDoesNotExistMessage, reqData.getActivityName());
        }else if (parishExist.isEmpty()){
            return String.format(parishDoesNotExistMessage, reqData.getParishName());
        }

        Beneficiary beneficiary = Beneficiary.builder()
                .houseHold(reqData.getBeneficiaryHousehold())
                .male(reqData.getBeneficiaryMale())
                .female(reqData.getBeneficiaryFemale())
                .build();
        beneficiaryRepository.save(beneficiary);

        Disability disability = Disability.builder()
                .male(reqData.getDisabilityMale())
                .female(reqData.getBeneficiaryFemale())
                .build();
        disabilityRepository.save(disability);

        Vulnerability vulnerability = Vulnerability.builder()
                .under18Female(reqData.getVulnerabilityUnder18Female())
                .under18Male(reqData.getVulnerabilityUnder18Male())
                .over60Female(reqData.getVulnerabilityOver60Female())
                .over60Male(reqData.getVulnerabilityOver60Male())
                .build();
        vulnerabilityRepository.save(vulnerability);

        BeneficiaryList beneficiaryList = BeneficiaryList.builder()
                .activity(activityExist.get())
                .parish(parishExist.get())
                .beneficiary(beneficiary)
                .disability(disability)
                .vulnerability(vulnerability)
                .build();
        return beneficiaryListRepository.save(beneficiaryList);
    }

    @Override
    public List<String> showAll() {
        return null;
    }

    @Override
    public BeneficiaryList findById(int id) {
        return beneficiaryListRepository.findById(id).orElse(null);
    }

    @Override
    public BeneficiaryList delete(int id) {
        return null;
    }

    @Override
    public Object update(BeneficiaryListDto reqData) {

        Optional<BeneficiaryList> beneficiaryListOptional = beneficiaryListRepository.findById(reqData.getId());
        Optional<Activity> activityExist = activityRepository.findByName(reqData.getActivityName());
        Optional<Parish> parishExist = parishRepository.findByName(reqData.getParishName());

        if (activityExist.isEmpty() && parishExist.isEmpty()){
            return String.format(bothDoesNotExistMessage, reqData.getActivityName(), reqData.getParishName());
        }
        else if(activityExist.isEmpty()){
            return String.format(activityDoesNotExistMessage, reqData.getActivityName());
        }else if (parishExist.isEmpty()){
            return String.format(parishDoesNotExistMessage, reqData.getParishName());
        }

        if (beneficiaryListOptional.isPresent()){

            Optional<Beneficiary> beneficiaryExist = beneficiaryRepository.findById(beneficiaryListOptional.get().getBeneficiary().getId());
            Optional<Disability> disabilityExist = disabilityRepository.findById(beneficiaryListOptional.get().getDisability().getId());
            Optional<Vulnerability> vulnerabilityExist = vulnerabilityRepository.findById(beneficiaryListOptional.get().getVulnerability().getId());

            if (beneficiaryExist.isPresent() && disabilityExist.isPresent() && vulnerabilityExist.isPresent()){

                /*Optional<BeneficiaryList> checkAlreadyExist = beneficiaryListRepository.findByActivityAndParish(reqData.getId(), activityExist.get().getId(), parishExist.get().getId());
                if (checkAlreadyExist.isPresent()){
                    if (
                        beneficiaryExist.get().getHouseHold() == reqData.getBeneficiaryHousehold() &&
                        beneficiaryExist.get().getFemale() == reqData.getBeneficiaryFemale() &&
                        beneficiaryExist.get().getMale() == reqData.getBeneficiaryMale() &&
                        disabilityExist.get().getMale() == reqData.getDisabilityMale() && disabilityExist.get().getFemale() == reqData.getDisabilityFemale() &&
                        vulnerabilityExist.get().getUnder18Male() == reqData.getVulnerabilityUnder18Male() &&
                        vulnerabilityExist.get().getUnder18Female() == reqData.getVulnerabilityUnder18Female() &&
                        vulnerabilityExist.get().getOver60Male() == reqData.getVulnerabilityOver60Male() &&
                        vulnerabilityExist.get().getOver60Female() == reqData.getVulnerabilityOver60Female()
                    ){
                        return String.format(existsMessage);
                    }
                }*/

                beneficiaryExist.get().setHouseHold(reqData.getBeneficiaryHousehold());
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
    public DataTablesOutput<BeneficiaryList> tableBeneficiaryList(DataTablesInput input) {
        return beneficiaryListRepository.findAll(input);
    }

}
