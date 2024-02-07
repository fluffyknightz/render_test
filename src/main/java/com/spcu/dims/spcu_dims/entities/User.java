package com.spcu.dims.spcu_dims.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @ManyToOne(targetEntity = Role.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_role"))
    private Role role;
}
