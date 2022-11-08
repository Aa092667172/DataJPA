package com.jpa.datajpa.many2many;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeacherClassRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherClassRepository teacherClassRepository;

    @Test
    void test(){
            TechClass techClass = TechClass.builder().title("國文課").build();
        System.out.println(techClass);
    }
}