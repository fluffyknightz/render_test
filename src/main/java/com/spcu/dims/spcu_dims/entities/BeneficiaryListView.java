package com.spcu.dims.spcu_dims.entities;

import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Immutable;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
@Immutable
@Table(name = "beneficiary_lists_view")
public class BeneficiaryListView {

    private String activityName;
    private String dioceseName;
    private String zoneName;
    private String parishName;
    private int beneficiaryHousehold;
    private int beneficiaryMale;
    private int beneficiaryFemale;
    private int disabilityMale;
    private int disabilityFemale;
    private int under18Male;
    private int under18Female;
    private int over60Male;
    private int over60Female;

}
