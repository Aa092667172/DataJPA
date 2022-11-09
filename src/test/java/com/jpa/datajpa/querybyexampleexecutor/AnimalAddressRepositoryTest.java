package com.jpa.datajpa.querybyexampleexecutor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.datajpa.enums.SexEnum;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AnimalAddressRepositoryTest {
    @Autowired
    private AnimalAddressRepository animalAddressRepository;

    @BeforeEach
    @Transactional
    @Rollback(false)
    void init(){
        Animal animal = Animal.builder()
                .name("jack")
                .sexEnum(SexEnum.BOY)
                .age(20)
                .createDate(Instant.now())
                .updateDate(new Date())
                .build();
        animalAddressRepository.saveAll(Lists.newArrayList(
                AnimalAddress.builder()
                        .animal(animal)
                        .address("板橋區")
                        .build(),
                AnimalAddress.builder()
                        .animal(animal)
                        .address("新莊區")
                        .build()
                ));
    }

    @Test
    @Rollback(value = false)
    void test() throws JsonProcessingException {
        Animal animal = Animal.builder()
                .name("jack")
                .age(20)
                .build();
        AnimalAddress address = AnimalAddress.builder()
                .animal(animal)
                .address("泰山區")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address));
        ExampleMatcher exampleMatcher= ExampleMatcher.matching().withMatcher("animal.name",ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("address",ExampleMatcher.GenericPropertyMatchers.startsWith());

        Page<AnimalAddress> u = animalAddressRepository.findAll(Example.of(address,exampleMatcher), PageRequest.of(0,2));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(u));
    }
}