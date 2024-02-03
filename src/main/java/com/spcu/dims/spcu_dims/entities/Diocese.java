package com.spcu.dims.spcu_dims.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "dioceses")
public class Diocese implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 10, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean isDelete;

//    @OneToMany(mappedBy = "diocese", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = Archdiocese.class)
//    @ToString.Exclude
//    @JsonIgnoreProperties("diocese")
//    private List<Archdiocese> archdioceseList = new ArrayList<>();

}
