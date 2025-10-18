package org.exarkun.acs_lab2.controllers;

import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.entities.Book;
import org.exarkun.acs_lab2.entities.BookStatus;
import org.exarkun.acs_lab2.entities.Person;
import org.exarkun.acs_lab2.services.BookService;
import org.exarkun.acs_lab2.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    private final PersonService personService;

    @GetMapping("/get-all")
    public String getAll(@RequestParam(name = "person-id", required = false) Long personId, Model model) {
        List<Book> books = service.findByPersonId(personId);
        model.addAttribute("books", books);
        return "book-page";
    }

    @GetMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Book book = service.findById(id);
        model.addAttribute("book", book);
        return "book-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {
        service.save(book);
        return "redirect:/book/get-all";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/book/get-all";
    }

    @GetMapping("/{id}/get-person")
    public String getPerson(@PathVariable Long id, Model model) {
        Book book = service.findById(id);
        model.addAttribute("book", book);
        if (book.getStatus() == BookStatus.AVAILABLE) {
            model.addAttribute("owner", new Person());
            model.addAttribute("people", personService.findAll());
        }
        return "book-person-form";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable Long id, @ModelAttribute Person owner) {
        service.assignPerson(id, owner);
        return "redirect:/book/%d/get-person".formatted(id);
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable Long id) {
        service.releasePerson(id);
        return "redirect:/book/%d/get-person".formatted(id);
    }
}
