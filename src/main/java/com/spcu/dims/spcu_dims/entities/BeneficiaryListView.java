package com.spcu.dims.spcu_dims.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Immutable
@Entity
@Table(name = "beneficiary_lists_view")
@Subselect("select * from beneficiary_lists_view")
public class BeneficiaryListView implements Serializable {

    @Id
    private int id;
    private LocalDate createdDate;
    private String createdUser;
    private String activityName;
    private String dioceseName;
    private String archdioceseName;
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
    private boolean isDelete;


}
