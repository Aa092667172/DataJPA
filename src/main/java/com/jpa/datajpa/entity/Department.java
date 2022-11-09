package com.jpa.datajpa.entity;

import com.jpa.datajpa.many2one.Student;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName="sno",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private List<Employee> employees;


}
