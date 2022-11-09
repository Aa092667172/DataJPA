package com.jpa.datajpa.dao;

import com.jpa.datajpa.dto.EmployeeOnlySnoNameDto;
import com.jpa.datajpa.entity.Department;
import com.jpa.datajpa.entity.Employee;
import com.jpa.datajpa.enums.Gender;
import com.jpa.datajpa.interfaces.EmployeeOnlySnoPhone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void insert(){
        Employee employee = new Employee();
        employee.setName("測試");
        employee.setPhone("09333333465");
//        employee.setDepartmentId(1l);
        employee.setGender(Gender.FEMALE);
        employeeRepository.saveAndFlush(employee);
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
        System.out.println(employeeRepository.testByQuery(1l));
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
        EmployeeOnlySnoPhone employeeOnlySnoPhone = employeeRepository.testJPQLToInterfaceByQuery(1l);
        System.out.println(employeeOnlySnoPhone.getId());
        System.out.println(employeeOnlySnoPhone.getPhone());
    }

    @Test
    void findDynamic(){
        assertEquals(employeeRepository.testJPQLByDynamicQuery(null,null,null).size(),2);
        assertEquals(employeeRepository.testJPQLByDynamicQuery(null,"鈺凱",null).size(),1);
    }
    @Test
    void findDynamic1(){
        assertEquals(employeeRepository.testJPQLByDynamicQuery1(null,"鈺凱",null).size(),1);
    }

    @Test
    void  findjoin(){
        Department department = new Department();
        department.setDepartmentName("haha test");
        departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setDepartmentId(department.getId());
        employee.setGender(Gender.MALE);
        employee.setName("阿森");
        employee.setPhone("0926332608");

        employeeRepository.save(employee);

        System.out.println(employeeRepository.findAll());
        System.out.println(departmentRepository.findAll());

    }

}
