package com.jpa.datajpa.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeOnlyNameRepositoryTest {
    @Autowired
    private  EmployeeOnlyNameRepository employeeOnlyNameRepository;

    @Test
    void test(){
        System.out.println(employeeOnlyNameRepository.findAll());
    }

}
