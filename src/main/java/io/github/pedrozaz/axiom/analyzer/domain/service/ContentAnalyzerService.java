package io.github.pedrozaz.axiom.analyzer.domain.service;

import io.github.pedrozaz.axiom.ingestor.domain.model.AnalysisResult;
import io.github.pedrozaz.axiom.ingestor.domain.model.Content;
import io.github.pedrozaz.axiom.ingestor.domain.model.Decision;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentAnalyzerService {

    private static final List<String> BANNED_WORDS = List.of("click here", "free", "test");

    public AnalysisResult analyze(Content content) {
        boolean containsBannedWords = BANNED_WORDS.stream()
                .anyMatch(word -> content.getTextContent().toLowerCase().contains(word));

        if (containsBannedWords) {
            return AnalysisResult.builder()
                    .decision(Decision.FLAG_MANUAL_REVIEW)
                    .reasoning("This content might have spam keywords.")
                    .spamScore(0.85)
                    .toxicityScore(0.1)
                    .build();
        }

        return AnalysisResult.builder()
                .decision(Decision.APPROVE)
                .reasoning("This content has passed all automatic analysis.")
                .spamScore(0.1)
                .toxicityScore(0.05)
                .build();
    }
}
