package com.spcu.dims.spcu_dims.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnualReturnDTO {

    private int id;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int year;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int resident;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int catechumen;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int chapel;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int visited;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int project;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int matrimonialCases;

    //    for Parish Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private String parish;

    //    for Baptized Catholic Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int indigenous;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int notIndigenous;

    //    for Non-Catholic Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int nonChristian;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int nonCatholic;

    //    for Priest Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int malePriest;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int femalePriest;

    //    for Catechist Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int paid;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int voluntary;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int homeMissioner;

    //    for Seminarian Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int major;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int minor;

    //    for Religious Life Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int male;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int female;

    //    for Baptism Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int above8Year;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int upTo7Year;

    //    For Marriage Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int betweenCatholic;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int withNonCatholic;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int withNonChristian;

    //    for Death Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int priest;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int religious;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int catholic;

    //    for Institute Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int seminary;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int novitiate;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int catechist;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int training;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int boarding;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int orphan;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int poor;

    //    for Priestly Ordination Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int profession;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int graduated;

    //    for Education Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int basic;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int middle;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int high;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int university;

    //    for Religious Activity Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int retreats;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int recollections;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int seminar;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int other;

    //    for Catholic Action Entity
    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int mary;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int vicent;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int parishElder;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int youthGroup;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int motherGuide;

    @NotNull(message = "Please fill require Data!.")
    @Min(value = 0, message = "Please fill require Data!")
    private int caOther;
}
