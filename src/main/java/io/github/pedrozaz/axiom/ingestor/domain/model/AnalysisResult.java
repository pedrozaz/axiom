package io.github.pedrozaz.axiom.ingestor.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class AnalysisResult {
    Double spamScore;
    Double toxicityScore;
    Decision decision;
    String reasoning;
}
