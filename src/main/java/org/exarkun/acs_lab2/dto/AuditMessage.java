package org.exarkun.acs_lab2.dto;

import org.exarkun.acs_lab2.entities.EventType;

import java.time.LocalDateTime;

public record AuditMessage(
        EventType eventType,
        String tableName,
        LocalDateTime dateTime,
        String info
) {}
