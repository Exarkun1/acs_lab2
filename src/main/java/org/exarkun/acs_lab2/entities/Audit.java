package org.exarkun.acs_lab2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit")
@Getter @Setter
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_seq")
    @SequenceGenerator(name = "audit_seq", sequenceName = "audit_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @Column(name = "info")
    private String info;
}
