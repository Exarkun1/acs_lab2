package org.exarkun.acs_lab2.controllers.api;

import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.converters.PersonConverter;
import org.exarkun.acs_lab2.dto.PersonDto;
import org.exarkun.acs_lab2.entities.Person;
import org.exarkun.acs_lab2.services.PersonService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonRestController {

    private final PersonService service;

    private final PersonConverter converter;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(@RequestHeader("Accept") String acceptHeader) {
        List<Person> people = service.findAll();
        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) || acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            return new ResponseEntity<>(converter.convertToXml(people), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.ok(converter.convertToDtoList(people));
        }
    }

    @GetMapping("/get-null")
    public ResponseEntity<?> getNull(@RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) || acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            return new ResponseEntity<>(converter.convertToXml(new Person()), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.ok(converter.convertToDto(new Person()));
        }
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<?> getById(@PathVariable Long id, @RequestHeader("Accept") String acceptHeader) {
        Person person = service.findById(id);
        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) || acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            return new ResponseEntity<>(converter.convertToXml(person), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.ok(converter.convertToDto(person));
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody PersonDto personDto) {
        service.save(converter.convertFromDto(personDto));
        return ResponseEntity.created(URI.create("/api/person/get-all")).build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
