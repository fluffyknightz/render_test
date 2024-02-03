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
@Table(name = "archdioceses")
public class Archdiocese implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 10, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean isDelete;

    @ManyToOne(targetEntity = Diocese.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "diocese_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_diocese"))
    private Diocese diocese;

//    @OneToMany(mappedBy = "archdiocese", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = Parish.class)
//    @ToString.Exclude
//    private List<Parish> parishList = new ArrayList<>();
}
