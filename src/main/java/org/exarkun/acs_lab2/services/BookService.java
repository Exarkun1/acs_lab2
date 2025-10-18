package org.exarkun.acs_lab2.services;

import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.entities.Book;
import org.exarkun.acs_lab2.entities.BookStatus;
import org.exarkun.acs_lab2.entities.Person;
import org.exarkun.acs_lab2.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public List<Book> findByPersonId(Long personId) {
        if (personId == null) {
            return repository.findAll();
        } else {
            return repository.findAllByPerson_Id(personId);
        }
    }

    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void save(Book book) {
        repository.save(book);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void assignPerson(Long id, Person person) {
        Book book = findById(id);
        book.setStatus(BookStatus.NOT_AVAILABLE);
        book.setPerson(person);
    }

    public void releasePerson(Long id) {
        Book book = findById(id);
        book.setStatus(BookStatus.AVAILABLE);
        book.setPerson(null);
    }
}
