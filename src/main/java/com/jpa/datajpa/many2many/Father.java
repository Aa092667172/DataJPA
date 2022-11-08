package com.jpa.datajpa.many2many;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Father {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "father")
    private List<ManFatherRelation> manFatherRelationList;
}
