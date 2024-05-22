package com.spcu.dims.spcu_dims.views;

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
@Table(name = "annual_returns_view")
@Subselect("select * from annual_returns_view")
public class AnnualReturnView implements Serializable {

    @Id
    private int id;
    private String parish;
    private int year;
    private int totalActualResident;
    private int indigenous;
    private int notIndigenous;
    private int bcTotal;
    private int nonChristian;
    private int nonCatholicChristian;
    private int ncTotal;
    private int catechumen;
    private int chapel;
    private int visitedPlace;
    private int malePriest;
    private int femalePriest;
    private int paid;
    private int voluntary;
    private int homeMissioner;
    private int major;
    private int minor;
    private int rlMale;
    private int rlFemale;
    private int above8Year;
    private int upTo7Year;
    private int betweenCatholic;
    private int withNonCatholicChristian;
    private int withNonChristian;
    private int priest;
    private int religious;
    private int catholic;
    private int seminary;
    private int novitiate;
    private int catechist;
    private int trainingForHomeMissioner;
    private int boardingForPoor;
    private int poor;
    private int orphan;
    private int religiousProfession;
    private int graduated;
    private int basic;
    private int middle;
    private int high;
    private int university;
    private int retreats;
    private int recollections;
    private int seminar;
    private int other;
    private int legionOfMary;
    private int stVincentDePaul;
    private int parishElder;
    private int youthGroup;
    private int motherGuide;
    private int caOther;
    private int project;
    private int matrimonialCases;
    private LocalDate createdDate;
    private String createdUser;
    private boolean isDelete;



}
