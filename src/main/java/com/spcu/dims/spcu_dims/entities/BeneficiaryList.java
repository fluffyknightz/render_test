package com.spcu.dims.spcu_dims.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "beneficiaries_list")
public class BeneficiaryList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private boolean isDelete;

    @ManyToOne(targetEntity = Parish.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "parish_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_parish"))
    private Parish parish;

    @OneToOne(targetEntity = Beneficiary.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "beneficiary_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_beneficiary"), unique = true)
    private Beneficiary beneficiary;

    @OneToOne(targetEntity = Disability.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "disability_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_disability"), unique = true)
    private Disability disability;

    @OneToOne(targetEntity = Vulnerability.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "vulnerability_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_vulnerability"), unique = true)
    private Vulnerability vulnerability;

    @OneToOne(targetEntity = Activity.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "activity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_activity"), unique = true)
    private Activity activity;
}
