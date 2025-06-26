package io.github.pedrozaz.axiom.analyzer.application.port.out;

import io.github.pedrozaz.axiom.ingestor.domain.model.Content;

public interface ContentEventPublisherPort {
    void publish(Content content);
}
