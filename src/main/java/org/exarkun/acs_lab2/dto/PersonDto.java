package org.exarkun.acs_lab2.dto;

public record PersonDto(
        Long id,
        String fullName,
        Integer yearOfBirth,
        String email
) {}
