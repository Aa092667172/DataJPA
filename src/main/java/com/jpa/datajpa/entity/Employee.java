package com.jpa.datajpa.entity;

import com.jpa.datajpa.enums.Gender;
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
    @Column(name = "department_id")
    private Long departmentId;
    //用於儲存枚舉名稱
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
}
