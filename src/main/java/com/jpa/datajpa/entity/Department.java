package com.jpa.datajpa.entity;

import com.jpa.datajpa.many2one.Student;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sno")
    private Long id;
    @Column(name = "name")
    private String departmentName;
}
