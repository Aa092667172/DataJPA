package com.jpa.datajpa.many2many;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ManFatherRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createTime,updateTime;
    @ManyToOne
    private Man man;
    @ManyToOne
    private Father father;
}
