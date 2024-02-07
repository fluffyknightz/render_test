package com.spcu.dims.spcu_dims.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "disabilities")
public class Disability implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0 NOT NULL")
    private int male;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0 NOT NULL")
    private int female;
}
