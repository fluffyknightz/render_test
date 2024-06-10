package com.spcu.dims.spcu_dims.entities.beneficiary_list;

import com.spcu.dims.spcu_dims.entities.Activity;
import com.spcu.dims.spcu_dims.entities.Congregation;
import com.spcu.dims.spcu_dims.entities.Parish;
import com.spcu.dims.spcu_dims.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "beneficiary_lists")
public class BeneficiaryList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private boolean isDelete;

    @Column(nullable = false)
    private LocalDate createdDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_user"), nullable = false)
    private User user;

    @ManyToOne(targetEntity = Parish.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "parish_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_parish"), nullable = false)
    private Parish parish;

    @ManyToOne(targetEntity = Activity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_activity"), nullable = false)
    private Activity activity;

    @ManyToOne(targetEntity = Congregation.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "congregation_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_congregation"), nullable = false)
    private Congregation congregation;

    @OneToOne(targetEntity = Beneficiary.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "beneficiary_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_beneficiary"), unique = true, nullable = false)
    private Beneficiary beneficiary;

    @OneToOne(targetEntity = Disability.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "disability_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_disability"), unique = true, nullable = false)
    private Disability disability;

    @OneToOne(targetEntity = Vulnerability.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "vulnerability_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_vulnerability"), unique = true, nullable = false)
    private Vulnerability vulnerability;

    @PrePersist
    private void prePersist() {
        this.createdDate = LocalDate.now();
    }

}
