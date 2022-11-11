package com.jpa.datajpa.querydsl;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderOnlyNameAndId {
    private Long id;
    private String orderName;
}
