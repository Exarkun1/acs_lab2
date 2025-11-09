package org.exarkun.acs_lab2.dto;

public record BookDto(
        Long id,
        String title,
        String author,
        String status,
        Long personId
) {}
