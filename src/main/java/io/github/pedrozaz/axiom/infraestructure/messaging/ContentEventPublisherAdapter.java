package io.github.pedrozaz.axiom.infraestructure.messaging;

import io.github.pedrozaz.axiom.application.port.out.ContentEventPublisherPort;
import io.github.pedrozaz.axiom.domain.model.Content;
import io.github.pedrozaz.axiom.infraestructure.messaging.dto.ContentSubmittedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ContentEventPublisherAdapter implements ContentEventPublisherPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;

    public ContentEventPublisherAdapter(KafkaTemplate<String, Object> kafkaTemplate,
                                        @Value("${topics.content.submitted}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void publishContentSubmittedEvent(Content content) {

        var event = new ContentSubmittedEvent(
                content.getId(),
                content.getUserId(),
                content.getTextContent(),
                content.getCreatedAt()
        );
        kafkaTemplate.send(topic, content.getId().toString(), event);
    }

    @Override
    public void publishContentAnalyzedEvent(Content content) {
    }
}
