package io.github.pedrozaz.axiom.application.port.out;

import io.github.pedrozaz.axiom.domain.model.Content;

public interface ContentEventPublisherPort {
    void publishContentSubmittedEvent(Content content);
    void publishContentAnalyzedEvent(Content content);
}
