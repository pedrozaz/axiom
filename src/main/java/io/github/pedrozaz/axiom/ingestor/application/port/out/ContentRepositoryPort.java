package io.github.pedrozaz.axiom.ingestor.application.port.out;

import io.github.pedrozaz.axiom.ingestor.domain.model.Content;

import java.util.Optional;
import java.util.UUID;

public interface ContentRepositoryPort {
    Content save(Content content);
    Optional<Content> findById(UUID id);
}
