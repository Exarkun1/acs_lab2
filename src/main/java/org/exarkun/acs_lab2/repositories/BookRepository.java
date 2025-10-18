package org.exarkun.acs_lab2.repositories;

import org.exarkun.acs_lab2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByPerson_Id(Long personId);
}
