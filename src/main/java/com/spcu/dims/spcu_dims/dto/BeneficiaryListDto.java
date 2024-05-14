package com.spcu.dims.spcu_dims.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class BeneficiaryListDto {
    private int id;

    @NotBlank
    private String activityName;

    @NotBlank
    private String parishName;

    @NotNull
    private LocalDate createdDate;

    @NotNull
    private int beneficiaryHousehold;

    @NotNull
    private int beneficiaryMale;

    @NotNull
    private int beneficiaryFemale;

    @NotNull
    private int disabilityMale;

    @NotNull
    private int disabilityFemale;

    @NotNull
    private int vulnerabilityUnder18Male;

    @NotNull
    private int vulnerabilityUnder18Female;

    @NotNull
    private int vulnerabilityOver60Male;

    @NotNull
    private int vulnerabilityOver60Female;
}
