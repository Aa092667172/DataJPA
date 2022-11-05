package com.jpa.datajpa.dao;

import com.jpa.datajpa.dto.EmployeeOnlySnoNameDto;
import com.jpa.datajpa.entity.Employee;
import com.jpa.datajpa.interfaces.EmployeeOnlySnoPhone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void insert(){
        Employee employee = new Employee();
        employee.setName("鈺凱");
        employee.setPhone("0919336777");
        employee.setDepartmentId(1l);
        employeeRepository.save(employee);
    }
    @Test
    void findAll(){
        System.out.println(employeeRepository.findAll());
    }

    @Test
    void findDto(){
        EmployeeOnlySnoNameDto employeeOnlySnoNameDto = employeeRepository.findByName("鈺凱");
        System.out.println(employeeOnlySnoNameDto);
    }

    @Test
    void findInterface(){
        EmployeeOnlySnoPhone byPhone = employeeRepository.findByPhone("0919336777");
        System.out.println(byPhone.getPhone());
        System.out.println(byPhone.getId());
    }

    @Test
    void findQuery(){
        System.out.println(employeeRepository.testByQuery(17l));
    }

    @Test
    void findNativeQuery(){
        System.out.println(employeeRepository.testNativeByQuery(17l));
    }

    @Test
    void findDTOByQuery(){
        EmployeeOnlySnoNameDto dto = employeeRepository.testJPQLToDtoByQuery(17l);
        System.out.println(dto);
    }


    @Test
    void findQueryInterface(){
        EmployeeOnlySnoPhone employeeOnlySnoPhone = employeeRepository.testJPQLToInterfaceByQuery(17l);
        System.out.println(employeeOnlySnoPhone.getId());
        System.out.println(employeeOnlySnoPhone.getPhone());
    }

}
