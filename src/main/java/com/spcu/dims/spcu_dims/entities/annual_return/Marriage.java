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
@Table(name = "marriages")
public class Marriage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int betweenCatholic;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int withNonCatholicChristian;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int withNonChristian;
}
