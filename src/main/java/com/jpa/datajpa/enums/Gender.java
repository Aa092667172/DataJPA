package com.jpa.datajpa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum Gender {
    MALE("男性"),
    FEMALE("女性");
    private final @Getter String value;

    public static Gender findEnum(String dbValue){
        return Arrays.stream(values()).filter(e->e.name().equals(dbValue))
                .findFirst().orElseThrow(()->new RuntimeException("查無此性別"));
    }
}
