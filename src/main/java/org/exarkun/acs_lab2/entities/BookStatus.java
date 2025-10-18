package org.exarkun.acs_lab2.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookStatus {
    AVAILABLE("Available"), NOT_AVAILABLE("Not available");

    private final String value;
}
