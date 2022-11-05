package com.jpa.datajpa.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeOnlyNameRepositoryTest {
    @Autowired
    private  EmployeeOnlyNameRepository employeeOnlyNameRepository;

    @Test
    void test(){
        System.out.println(employeeOnlyNameRepository.findAll());
    }

}
