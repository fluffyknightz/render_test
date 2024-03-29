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
@Table(name = "beneficiary_lists")
public class BeneficiaryList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private boolean isDelete;

    @ManyToOne(targetEntity = Parish.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "parish_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_parish"))
    private Parish parish;

    @ManyToOne(targetEntity = Activity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_activity"))
    private Activity activity;

    @OneToOne(targetEntity = Beneficiary.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "beneficiary_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_beneficiary"), unique = true)
    private Beneficiary beneficiary;

    @OneToOne(targetEntity = Disability.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "disability_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_disability"), unique = true)
    private Disability disability;

    @OneToOne(targetEntity = Vulnerability.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "vulnerability_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_vulnerability"), unique = true)
    private Vulnerability vulnerability;


}
