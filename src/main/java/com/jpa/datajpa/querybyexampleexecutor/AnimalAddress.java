package com.jpa.datajpa.querybyexampleexecutor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "Animal")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Animal animal;
}
