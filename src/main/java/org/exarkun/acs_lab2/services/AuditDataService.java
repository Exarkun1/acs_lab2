package org.exarkun.acs_lab2.services;

import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.dto.AuditMessage;
import org.exarkun.acs_lab2.entities.Audit;
import org.exarkun.acs_lab2.repositories.AuditRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuditDataService {

    private final AuditRepository repository;

    @Transactional
    @JmsListener(destination = "${topic.name}")
    public void save(AuditMessage message) {
        Audit audit = new Audit();
        audit.setEventType(message.eventType());
        audit.setTableName(message.tableName());
        audit.setDateTime(message.dateTime());
        audit.setInfo(message.info());
        repository.save(audit);
    }
}
