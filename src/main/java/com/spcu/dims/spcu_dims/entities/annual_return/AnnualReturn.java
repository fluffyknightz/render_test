package com.spcu.dims.spcu_dims.entities.annual_return;

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
@Table(name = "annual_returns")
public class AnnualReturn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private boolean isDelete;

    @Column(nullable = false, unique = true, columnDefinition = "YEAR")
    private int year;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int totalActualResident;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int catechumen;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int chapel;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int visitedPlace;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int project;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int matrimonialCases;

    @Column(nullable = false)
    private LocalDate createdDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "created_user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_user_2"), nullable = false)
    private User createdUser;

    @ManyToOne(targetEntity = Parish.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "parish_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_parish_2"), nullable = false)
    private Parish parish;

    @OneToOne(targetEntity = BaptizedCatholic.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "baptized_catholic_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_baptized_catholic"), unique = true, nullable = false)
    private BaptizedCatholic baptizedCatholic;

    @OneToOne(targetEntity = NonCatholic.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "non_catholic_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_non_catholic"), unique = true, nullable = false)
    private NonCatholic nonCatholic;

    @OneToOne(targetEntity = Priest.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "priest_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_priest"), unique = true, nullable = false)
    private Priest priest;

    @OneToOne(targetEntity = Catechist.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "catechist_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_catechist"), unique = true, nullable = false)
    private Catechist catechist;

    @OneToOne(targetEntity = Seminarian.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "seminarian_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_seminarian"), unique = true, nullable = false)
    private Seminarian seminarian;

    @OneToOne(targetEntity = ReligiousLife.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "religious_life_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_religious_life"), unique = true, nullable = false)
    private ReligiousLife religiousLife;

    @OneToOne(targetEntity = Baptism.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "baptism_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_baptism"), unique = true, nullable = false)
    private Baptism baptism;

    @OneToOne(targetEntity = Marriage.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "marriage_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_marriage"), unique = true, nullable = false)
    private Marriage marriage;

    @OneToOne(targetEntity = Death.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "death_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_death"), unique = true, nullable = false)
    private Death death;

    @OneToOne(targetEntity = Institute.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "institute_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_institute"), unique = true, nullable = false)
    private Institute institute;

    @OneToOne(targetEntity = PriestlyOrdination.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "priestly_ordination_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_priestly_ordination"), unique = true, nullable = false)
    private PriestlyOrdination priestlyOrdination;

    @OneToOne(targetEntity = Education.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "education_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_education"), unique = true, nullable = false)
    private Education education;

    @OneToOne(targetEntity = ReligiousActivity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "religious_activity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_religious_activity"), unique = true, nullable = false)
    private ReligiousActivity religiousActivity;

    @OneToOne(targetEntity = CatholicAction.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "catholic_action_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_catholic_action"), unique = true, nullable = false)
    private CatholicAction catholicAction;


    @PrePersist
    private void prePersist(){
        this.createdDate = LocalDate.now();
    }
}