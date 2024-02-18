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
@Table(name = "parishes")
public class Parish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private boolean isDelete;

    @Column(columnDefinition = "LONGTEXT")
    private String note;

    @ManyToOne(targetEntity = Zone.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "zone_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_zone"))
    private Zone zone;

    @ManyToOne(targetEntity = Township.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "township_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_township"))
    private Township township;



}
