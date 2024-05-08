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
@Table(name = "institutes")
public class Institute implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int seminary;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int novitiate;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int catechist;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int trainingForHomeMissioner;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int boardingForPoor;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int orphan;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int poor;

}
