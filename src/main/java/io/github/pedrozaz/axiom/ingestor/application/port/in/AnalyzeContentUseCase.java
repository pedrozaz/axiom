package io.github.pedrozaz.axiom.ingestor.application.port.in;

import java.util.UUID;

public interface AnalyzeContentUseCase {
    void analyzeContent(UUID contentId);
}
