package com.spcu.dims.spcu_dims.entities.beneficiary_list;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "beneficiaries")
public class Beneficiary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int household;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int male;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int female;

}
