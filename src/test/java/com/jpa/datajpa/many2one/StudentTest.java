package com.jpa.datajpa.many2one;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentTest {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentAddressRepository studentAddressRepository;

    @BeforeEach
    @Transactional
    @Rollback(false)
    void init(){
        Student student = Student.builder().name("jackxx")
                .email("123456@123.com").build();
        StudentAddress studentAddress = StudentAddress.builder()
                .address("我是地址1111")
                .student(student)
                .build();

        StudentAddress studentAddress1 = StudentAddress.builder()
                .address("我是地址2222")
                .student(student)
                .build();
        studentAddressRepository.saveAll(Lists.newArrayList(studentAddress,studentAddress1));
    }

    @Test
    @Rollback(value = false)
    void testStudentRelationships(){
        Student student = studentRepository.getReferenceById(79l);
        System.out.println(student.getName());
        System.out.println(student.getAddress());
    }
}
