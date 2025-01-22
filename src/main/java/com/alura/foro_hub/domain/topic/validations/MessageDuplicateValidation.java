package com.alura.foro_hub.domain.topic.validations;

import com.alura.foro_hub.domain.topic.Topic;
import com.alura.foro_hub.domain.topic.TopicRepository;
import com.alura.foro_hub.domain.topic.dtos.DtoRegisterTopic;
import com.alura.foro_hub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageDuplicateValidation implements TopicValidator{
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void validate(DtoRegisterTopic dtoRegisterTopic) {
        Optional<Topic> topic = topicRepository.findByMessage(dtoRegisterTopic.message());

        if (topic.isPresent()) {
            throw new IntegrityValidation("There is already a topic with this same message");
        }
    }
}