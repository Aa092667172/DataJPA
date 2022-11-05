package com.jpa.datajpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sno")
    //名稱故意與db不相同 測試dto與Interface使用上的結果
    private Long id;
    private String name;
    private String phone;
    private Long departmentId;

}
