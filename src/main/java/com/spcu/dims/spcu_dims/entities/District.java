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
@Table(name = "districts")
public class District implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private boolean isDelete;

    @ManyToOne(targetEntity = Region.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "region_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_region"))
    private Region region;


}
