package io.github.pedrozaz.axiom.analyzer.infraestructure.messaging.dto;

import io.github.pedrozaz.axiom.ingestor.domain.model.Decision;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContentAnalyzedEvent(
        UUID contentId,
        Decision decision,
        String reasoning,
        Double spamScore,
        Double toxicityScore,
        LocalDateTime analyzedAt
) { }
