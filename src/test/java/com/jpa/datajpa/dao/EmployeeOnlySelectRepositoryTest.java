package com.jpa.datajpa.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeOnlySelectRepositoryTest {
    @Autowired
    private EmployeeOnlySelectRepository employeeOnlySelectRepository;
    @Test
    void test(){
        System.out.println(employeeOnlySelectRepository.findAll());
        System.out.println(employeeOnlySelectRepository.findById(17l));
    }
}
