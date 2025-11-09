package org.exarkun.acs_lab2.converters;

import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.dto.BookDto;
import org.exarkun.acs_lab2.entities.Book;
import org.exarkun.acs_lab2.entities.BookStatus;
import org.exarkun.acs_lab2.entities.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookConverter {
    private static final String BOOK_FORM_XSL_PATH = "/xsl/book-form.xsl";

    private static final String BOOK_PAGE_XSL_PATH = "/xsl/book-page.xsl";

    private final XmlTransformer xmlTransformer;

    public BookDto convertToDto(Book book) {
        Person person = book.getPerson();
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getStatus().getValue(),
                (person != null ? person.getId() : null)
        );
    }

    public List<BookDto> convertToDtoList(List<Book> books) {
        return books.stream().map(this::convertToDto).toList();
    }

    public String convertToXml(Book book) {
        BookDto dto = convertToDto(book);
        return xmlTransformer.transformToXml(dto, "book", BOOK_FORM_XSL_PATH);
    }

    public String convertToXml(List<Book> books) {
        List<BookDto> dtoList = convertToDtoList(books);
        return xmlTransformer.transformToXml(dtoList, "books", BOOK_PAGE_XSL_PATH);
    }

    public Book convertFromDto(BookDto dto) {
        Book book = new Book();
        book.setId(dto.id());
        book.setTitle(dto.title());
        book.setAuthor(dto.author());

        if (dto.personId() != null) {
            Person person = new Person();
            person.setId(dto.personId());
            book.setPerson(person);
            book.setStatus(BookStatus.NOT_AVAILABLE);
        } else {
            book.setPerson(null);
            book.setStatus(BookStatus.AVAILABLE);
        }
        return book;
    }
}
