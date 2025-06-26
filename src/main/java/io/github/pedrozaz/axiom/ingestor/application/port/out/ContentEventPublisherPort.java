package io.github.pedrozaz.axiom.ingestor.application.port.out;

import io.github.pedrozaz.axiom.ingestor.domain.model.Content;

public interface ContentEventPublisherPort {
    void publishContentSubmittedEvent(Content content);
    void publishContentAnalyzedEvent(Content content);
}
