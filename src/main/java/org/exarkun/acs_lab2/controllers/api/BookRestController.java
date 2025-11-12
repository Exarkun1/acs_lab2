package org.exarkun.acs_lab2.controllers.api;

import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.converters.BookConverter;
import org.exarkun.acs_lab2.dto.BookDto;
import org.exarkun.acs_lab2.entities.Book;
import org.exarkun.acs_lab2.services.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService service;

    private final BookConverter converter;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(@RequestParam(name = "person-id", required = false) Long personId, @RequestHeader("Accept") String acceptHeader) {
        List<Book> books = service.findByPersonId(personId);
        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) || acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            return new ResponseEntity<>(converter.convertToXml(books), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.ok(converter.convertToDtoList(books));
        }
    }

    @GetMapping("/get-null")
    public ResponseEntity<?> getNull(@RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) || acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            return new ResponseEntity<>(converter.convertToXml(new Book()), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.ok(converter.convertToDto(new Book()));
        }
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<?> getById(@PathVariable Long id, @RequestHeader("Accept") String acceptHeader) {
        Book book = service.findById(id);
        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) || acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            return new ResponseEntity<>(converter.convertToXml(book), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.ok(converter.convertToDto(book));
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody BookDto bookDto) {
        service.save(converter.convertFromDto(bookDto));
        return ResponseEntity.created(URI.create("/api/person/get-all")).build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
