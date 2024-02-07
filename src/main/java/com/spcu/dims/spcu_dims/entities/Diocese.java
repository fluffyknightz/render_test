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
