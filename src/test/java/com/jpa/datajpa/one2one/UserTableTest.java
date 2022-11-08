package com.jpa.datajpa.one2one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTableTest {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Test
    @Rollback(value = false)
    void testUserRelation(){
        UserTest userTest = UserTest.builder().name("jackxx").email("123456@126.com").build();
        UserInfo userInfo = UserInfo.builder().ages(12).userTest(userTest).telephone("1234567").build();
        userInfoRepository.saveAndFlush(userInfo);
//        userInfo.setAges(13);
//        userInfo.setUser(null);
//        userInfoRepository.delete(userInfo);
    }

    @BeforeEach
    @Transactional
    @Rollback(false)
    void init(){
        UserTest userTest = UserTest.builder().name("jackxx").email("123456@123.com").build();
        UserInfo userInfo = UserInfo.builder().ages(12).telephone("12345678").userTest(userTest).build();
        userInfoRepository.saveAndFlush(userInfo);
    }

    @Test
    @Rollback(false)
    public void testUserRelationships(){
        UserInfo userInfo = userInfoRepository.getReferenceById(1l);
        System.out.println(userInfo);
        System.out.println(userInfo.getUserTest().getId());
    }
}
