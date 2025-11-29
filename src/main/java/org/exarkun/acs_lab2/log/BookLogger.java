package org.exarkun.acs_lab2.log;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.converters.BookConverter;
import org.exarkun.acs_lab2.entities.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class BookLogger implements BaseLogger {

    private final JmsTemplate jmsTemplate;

    private final BookConverter converter;

    @Value("${topic.name}")
    private String topicName;

    @Override
    public String getConvertedEntity(Object entity) {
        if (entity instanceof Book book) {
            return converter.convertToDto(book).toString();
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
