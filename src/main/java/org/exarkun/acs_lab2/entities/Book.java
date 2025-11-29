package org.exarkun.acs_lab2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.exarkun.acs_lab2.log.BookLogger;

@Entity
@Table(name = "book")
@EntityListeners(BookLogger.class)
@Setter @Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

}
