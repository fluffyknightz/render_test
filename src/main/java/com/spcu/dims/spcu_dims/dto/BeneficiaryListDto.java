package com.spcu.dims.spcu_dims.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class BeneficiaryListDto {
    private int id;

    @NotBlank(message = "Please Fill Require Data")
    private String activityName;

    @NotBlank(message = "Please Fill Require Data")
    private String parishName;

    @NotNull(message = "Please Fill Require Data")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int beneficiaryHousehold;

    @NotNull(message = "Please Fill Require Data")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int beneficiaryMale;

    @NotNull(message = "Please Fill Require Data")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int beneficiaryFemale;

    @NotNull(message = "Please Fill Require Data")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int disabilityMale;

    @NotNull(message = "Please Fill Require Data")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int disabilityFemale;

    @NotNull(message = "Please Fill Require Data")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int vulnerabilityUnder18Male;

    @NotNull(message = "Please Fill Require Data")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int vulnerabilityUnder18Female;

    @NotNull(message = "Please Fill Require Data")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int vulnerabilityOver60Male;

    @NotNull(message = "Please Fill Require Data")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int vulnerabilityOver60Female;
}
