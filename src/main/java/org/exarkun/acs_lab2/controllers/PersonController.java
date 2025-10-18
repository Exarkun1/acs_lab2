package org.exarkun.acs_lab2.controllers;

import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.entities.Person;
import org.exarkun.acs_lab2.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("/get-all")
    public String getAll(Model model) {
        List<Person> people = service.findAll();
        model.addAttribute("people", people);
        return "person-page";
    }

    @GetMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Person person = service.findById(id);
        model.addAttribute("person", person);
        return "person-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Person person) {
        service.save(person);
        return "redirect:/person/get-all";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/person/get-all";
    }
}
