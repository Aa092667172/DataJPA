package com.jpa.datajpa.one2one;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String sex;
    private String address;
    //外部關聯的屬性名稱 非sql表格
//    @OneToOne(mappedBy = "userTest")
//    private UserInfo userInfo;
}
