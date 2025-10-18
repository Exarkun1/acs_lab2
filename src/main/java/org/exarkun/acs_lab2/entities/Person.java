package org.exarkun.acs_lab2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "person")
@Setter @Getter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "year_of_birth")
    private Integer yearOfBirth;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

}
