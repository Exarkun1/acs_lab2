package org.exarkun.acs_lab2.converters;

import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.dto.PersonDto;
import org.exarkun.acs_lab2.entities.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonConverter {

    private static final String PERSON_FORM_XSL_PATH = "/xsl/person-form.xsl";

    private static final String PERSON_PAGE_XSL_PATH = "/xsl/person-page.xsl";

    private final XmlTransformer xmlTransformer;

    public PersonDto convertToDto(Person person) {
        return new PersonDto(
                person.getId(),
                person.getFullName(),
                person.getYearOfBirth(),
                person.getEmail()
        );
    }

    public List<PersonDto> convertToDtoList(List<Person> people) {
        return people.stream().map(this::convertToDto).toList();
    }

    public String convertToXml(Person person) {
        PersonDto dto = convertToDto(person);
        return xmlTransformer.transformToXml(dto, "person", PERSON_FORM_XSL_PATH);
    }

    public String convertToXml(List<Person> people) {
        List<PersonDto> dtoList = convertToDtoList(people);
        return xmlTransformer.transformToXml(dtoList, "people", PERSON_PAGE_XSL_PATH);
    }

    public Person convertFromDto(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.id());
        person.setFullName(personDto.fullName());
        person.setYearOfBirth(personDto.yearOfBirth());
        person.setEmail(personDto.email());
        return person;
    }
}
