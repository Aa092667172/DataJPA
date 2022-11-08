package com.jpa.datajpa.many2many;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 多個老師
 * 當兩方都是ManyToMany時 Hibernate將會自動多建立一張表
 * 因兩張表都是多對多 是違背建表原則的
 * 需要在中間建立一張外建表使 雙方關係為多對一
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "Teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //讓課程維護老師的關係
    @ManyToMany(mappedBy = "teachers")
    private List<TechClass> classes;
}
