package com.spcu.dims.spcu_dims.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnualReturnDTO {

    private int id;

    private boolean isDelete;

    private int year;

    private int totalActualResident;

    private int catechumen;

    private int chapel;

    private int visitedPlace;

    private int project;

    private int matrimonialCases;

//    for Parish Entity
    private String parish;

//    for Baptized Catholic Entity
    private int indigenous;

    private int notIndigenous;

//    for Non-Catholic Entity
    private int nonChristian;

    private int nonCatholicChristian;

//    for Priest Entity
    private int malePriest;

    private int femalePriest;

//    for Catechist Entity
    private int paid;

    private int voluntary;

    private int homeMissioner;

//    for Seminarian Entity
    private int major;

    private int minor;

//    for Religious Life Entity
    private int male;

    private int female;

//    for Baptism Entity
    private int above8Year;

    private int upTo7Year;

//    For Marriage Entity
    private int betweenCatholic;

    private int withNonCatholicChristian;

    private int withNonChristian;

//    for Death Entity
    private int priest;

    private int religious;

    private int catholic;

//    for Institute Entity
    private int seminary;

    private int novitiate;

    private int catechist;

    private int trainingForHomeMissioner;

    private int boardingForPoor;

    private int orphan;

    private int poor;

//    for Priestly Ordination Entity
    private int religiousProfession;

    private int graduated;

//    for Education Entity
    private int basic;

    private int middle;

    private int high;

    private int university;

//    for Religious Activity Entity
    private int retreats;

    private int recollections;

    private int seminar;

    private int other;

//    for Catholic Action Entity
    private int legionOfMary;

    private int stVincentDePaul;

    private int parishElder;

    private int youthGroup;

    private int motherGuide;

    private int caOther;
}
