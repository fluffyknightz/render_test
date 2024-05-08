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
@Table(name = "deaths")
public class Death implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int priest;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int religious;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int catholic;
}
