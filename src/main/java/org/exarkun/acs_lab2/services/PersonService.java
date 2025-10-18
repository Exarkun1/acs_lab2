package org.exarkun.acs_lab2.services;

import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.entities.BookStatus;
import org.exarkun.acs_lab2.entities.Person;
import org.exarkun.acs_lab2.repositories.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    public void save(Person person) {
        repository.save(person);
    }

    public void deleteById(Long id) {
        Person person = findById(id);
        Hibernate.initialize(person.getBooks());
        person.getBooks().forEach(book -> {
            book.setStatus(BookStatus.AVAILABLE);
            book.setPerson(null);
        });
        repository.delete(person);
    }
}
