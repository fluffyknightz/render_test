package com.spcu.dims.spcu_dims.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnualReturnDTO {

    private int id;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 1, message = "Please Add Require Minimum Value!")
    private int year;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int totalActualResident;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int catechumen;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int chapel;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int visitedPlace;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int project;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int matrimonialCases;

    //    for Parish Entity
    @NotEmpty(message = "Please fill require Data!")
    private String parish;

    //    for Baptized Catholic Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int indigenous;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int notIndigenous;

    //    for Non-Catholic Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int nonChristian;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int nonCatholicChristian;

    //    for Priest Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int malePriest;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int femalePriest;

    //    for Catechist Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int paid;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int voluntary;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int homeMissioner;

    //    for Seminarian Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int major;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int minor;

    //    for Religious Life Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int rlMale;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int rlFemale;

    //    for Baptism Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int above8Year;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int upTo7Year;

    //    For Marriage Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int betweenCatholic;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int withNonCatholicChristian;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int withNonChristian;

    //    for Death Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int priest;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int religious;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int catholic;

    //    for Institute Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int seminary;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int novitiate;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int catechist;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int trainingForHomeMissioner;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int boardingForPoor;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int orphan;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int poor;

    //    for Priestly Ordination Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int religiousProfession;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int graduated;

    //    for Education Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int basic;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int middle;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int high;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int university;

    //    for Religious Activity Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int retreats;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int recollections;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int seminar;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int other;

    //    for Catholic Action Entity
    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int legionOfMary;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int stVincentDePaul;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int parishElder;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int youthGroup;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int motherGuide;

    @NotNull(message = "Please fill require Data!")
    @Min(value = 0, message = "Please Add Require Minimum Value!")
    private int caOther;
}
