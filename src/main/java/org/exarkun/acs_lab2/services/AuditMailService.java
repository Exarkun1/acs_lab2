package org.exarkun.acs_lab2.services;

import lombok.RequiredArgsConstructor;
import org.exarkun.acs_lab2.dto.AuditMessage;
import org.exarkun.acs_lab2.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditMailService {

    private final JavaMailSender mailSender;

    private final PersonRepository personRepository;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @JmsListener(destination = "${topic.name}")
    public void send(AuditMessage message) {
        if (message.tableName().equals("book")) {
            personRepository.findAll().forEach(person -> {
                switch (message.eventType()) {
                    case INSERT -> sendSimpleMail(person.getEmail(), "Появилась новая книга", message.info());
                    case UPDATE -> sendSimpleMail(person.getEmail(), "Изменения в книге", message.info());
                    case DELETE -> sendSimpleMail(person.getEmail(), "Удалена книга", message.info());
                }
            });
        }
    }

    private void sendSimpleMail(String toAddress, String title, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailFrom);
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(text);
        mailSender.send(simpleMailMessage);
    }
}
