package com.spcu.dims.spcu_dims.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Immutable
@Entity
@Table(name = "counts_view")
@Subselect("select * from counts_view")
public class CountView implements Serializable {

    @Id
    private int id;
    private long archdiocese;
    private long diocese;
    private long zone;
    private long parish;
    private long region;
    private long district;
    private long township;
    private long activity;
    private long beneficiaryList;
    private long congregation;

}
