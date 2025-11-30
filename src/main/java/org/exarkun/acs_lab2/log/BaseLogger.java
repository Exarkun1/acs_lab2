package org.exarkun.acs_lab2.log;

import org.exarkun.acs_lab2.dto.AuditMessage;
import org.exarkun.acs_lab2.entities.EventType;
import org.springframework.jms.core.JmsTemplate;

import java.time.LocalDateTime;

public interface BaseLogger {

    String getTopicName();

    JmsTemplate getJmsTemplate();

    String getConvertedEntity(Object entity);

    default void onPersist(Object entity) {
        getJmsTemplate().convertAndSend(getTopicName(), getAuditMessage(entity, EventType.INSERT));
    }

    default void onUpdate(Object entity) {
        getJmsTemplate().convertAndSend(getTopicName(), getAuditMessage(entity, EventType.UPDATE));
    }

    default void onRemove(Object entity) {
        getJmsTemplate().convertAndSend(getTopicName(), getAuditMessage(entity, EventType.DELETE));
    }

    private AuditMessage getAuditMessage(Object entity, EventType eventType) {
        return new AuditMessage(
                eventType,
                entity.getClass().getSimpleName().toLowerCase(),
                LocalDateTime.now(),
                getConvertedEntity(entity)
        );
    }
}
