package com.jpa.datajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//如需要Repository返回DTO務必只能寫一個全參數的建構子
@AllArgsConstructor
public class EmployeeOnlySnoNameDto {
    private Long id;
    private String name;
}
