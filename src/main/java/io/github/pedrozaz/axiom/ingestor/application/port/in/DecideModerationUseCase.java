package io.github.pedrozaz.axiom.ingestor.application.port.in;

import io.github.pedrozaz.axiom.ingestor.domain.model.Decision;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

public interface DecideModerationUseCase {
    void decide(DecideCommand command);

    @Value
    @Builder
    class DecideCommand {
        UUID contentId;
        Decision decision;
        String reasoning;
    }
}
