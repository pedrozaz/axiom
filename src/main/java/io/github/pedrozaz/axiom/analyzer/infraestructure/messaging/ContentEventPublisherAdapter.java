package io.github.pedrozaz.axiom.analyzer.infraestructure.messaging;

import io.github.pedrozaz.axiom.analyzer.application.port.out.ContentEventPublisherPort;
import io.github.pedrozaz.axiom.analyzer.infraestructure.messaging.dto.ContentAnalyzedEvent;
import io.github.pedrozaz.axiom.ingestor.domain.model.Content;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ContentEventPublisherAdapter implements ContentEventPublisherPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;

    public ContentEventPublisherAdapter(KafkaTemplate<String, Object> kafkaTemplate,
                                        @Value("${topics.content.analyzed}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void publish(Content content) {
        var event = new ContentAnalyzedEvent(
                content.getId(),
                content.getAnalysisResult().getDecision(),
                content.getAnalysisResult().getReasoning(),
                content.getAnalysisResult().getSpamScore(),
                content.getAnalysisResult().getToxicityScore(),
                content.getUpdatedAt()
        );
        kafkaTemplate.send(topic, content.getId().toString(), event);
    }
}
