package io.github.pedrozaz.axiom.analyzer.adapter.in.kafka;

import io.github.pedrozaz.axiom.analyzer.adapter.in.kafka.dto.ContentSubmittedEvent;
import io.github.pedrozaz.axiom.analyzer.application.port.in.AnalyzeContentUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContentKafkaListener {

    private final AnalyzeContentUseCase analyzeContentUseCase;

    public ContentKafkaListener(AnalyzeContentUseCase analyzeContentUseCase) {
        this.analyzeContentUseCase = analyzeContentUseCase;
    }

    @KafkaListener(
            topics = "${topics.content.submitted}",
            groupId = "axiom-analyzer-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void onContentSubmitted(@Payload ContentSubmittedEvent event) {
        log.info("Event received: {} ", event.contentId());
        analyzeContentUseCase.analyzeContent(event.contentId());
    }
}
