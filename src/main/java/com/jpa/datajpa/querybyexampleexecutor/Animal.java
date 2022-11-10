package com.jpa.datajpa.querybyexampleexecutor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jpa.datajpa.enums.SexEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString(exclude = "AnimalAddress")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private SexEnum sexEnum;
    private Integer age;
    private Instant createDate;
    private Date updateDate;
    @OneToMany(mappedBy = "animal",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AnimalAddress> animalAddresses;
}
