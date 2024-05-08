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
@Table(name = "catholic_actions")
public class CatholicAction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int legionOfMary;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int stVincentDePaul;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int parishElder;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int youthGroup;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int motherGuide;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int other;

}
