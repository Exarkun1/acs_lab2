package org.exarkun.acs_lab2.log;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.converters.PersonConverter;
import org.exarkun.acs_lab2.entities.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class PersonLogger implements BaseLogger {

    private final JmsTemplate jmsTemplate;

    private final PersonConverter converter;

    @Value("${topic.name}")
    private String topicName;

    @Override
    public String getConvertedEntity(Object entity) {
        if (entity instanceof Person person) {
            return converter.convertToDto(person).toString();
        } else throw new IllegalArgumentException("Entity is not of type Person");
    }

    @PostPersist
    @Override
    public void onPersist(Object entity) {
        BaseLogger.super.onPersist(entity);
    }

    @PostUpdate
    @Override
    public void onUpdate(Object entity) {
        BaseLogger.super.onUpdate(entity);
    }

    @PostRemove
    @Override
    public void onRemove(Object entity) {
        BaseLogger.super.onRemove(entity);
    }
}
