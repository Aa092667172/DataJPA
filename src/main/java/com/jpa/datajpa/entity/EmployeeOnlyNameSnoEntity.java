package com.jpa.datajpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
@Data
/**
 * 如希望表格操作或查詢只針對某些欄位可額外寫一個Entity
 * 此種做法較不推薦 因需額外寫一個Entity與Repository
 * 且可能會有多Repository針對同張表的增刪改問題
 */
public class EmployeeOnlyNameSnoEntity {
    @Id
    @Column(name = "sno")
    private Long id;
    private String name;
}
