package com.jpa.datajpa.many2many;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 多個課程
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "TechClass")
public class TechClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany
    //joinTable的name可以控制多對一表的名稱
    //joinColumn 控制外來建名稱
    @JoinTable(name = "tech_class_ref",
           joinColumns =  @JoinColumn(name = "classes_id_x"),
            inverseJoinColumns = @JoinColumn(name = "teachers_id_x")
    )
    private List<Teacher> teachers;
}
