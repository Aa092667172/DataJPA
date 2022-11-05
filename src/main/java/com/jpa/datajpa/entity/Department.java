package com.jpa.datajpa.entity;

import lombok.Data;

import javax.persistence.*;

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
