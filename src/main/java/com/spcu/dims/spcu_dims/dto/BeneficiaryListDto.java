package com.spcu.dims.spcu_dims.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class BeneficiaryListDto {
    private int id;
    private String activityName;
    private String parishName;
    private LocalDate createdDate;
    private int beneficiaryHousehold;
    private int beneficiaryMale;
    private int beneficiaryFemale;
    private int disabilityMale;
    private int disabilityFemale;
    private int vulnerabilityUnder18Male;
    private int vulnerabilityUnder18Female;
    private int vulnerabilityOver60Male;
    private int vulnerabilityOver60Female;
}
