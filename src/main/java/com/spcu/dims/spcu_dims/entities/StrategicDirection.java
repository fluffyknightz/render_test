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
@Table(name = "strategic_directions")
public class StrategicDirection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private boolean isDelete;

    @ManyToOne(targetEntity = Factor.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "factor_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_factor"))
    private Factor factor;

    @ManyToOne(targetEntity = SDDetail.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sd_details_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_sd_details"))
    private SDDetail sdDetail;
}
