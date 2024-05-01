package com.spcu.dims.spcu_dims.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "dioceses")
public class Diocese implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 10, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private boolean isDelete;

    @ManyToOne(targetEntity = Archdiocese.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "archdiocese_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_archdiocese"))
    private Archdiocese archdiocese;


}
