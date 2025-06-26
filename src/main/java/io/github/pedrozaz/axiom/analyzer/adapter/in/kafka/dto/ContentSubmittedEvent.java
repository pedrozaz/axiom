package io.github.pedrozaz.axiom.analyzer.adapter.in.kafka.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContentSubmittedEvent(
        UUID contentId,
        UUID userId,
        String textContent,
        LocalDateTime submittedAt
) { }