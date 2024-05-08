package com.spcu.dims.spcu_dims.entities.annual_return;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "religious_activities")
public class ReligiousActivity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int retreats;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int recollections;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int seminar;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int other;

}
