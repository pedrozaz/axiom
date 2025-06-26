package io.github.pedrozaz.axiom.analyzer.application;

import io.github.pedrozaz.axiom.analyzer.application.port.in.AnalyzeContentUseCase;
import io.github.pedrozaz.axiom.analyzer.application.port.out.ContentEventPublisherPort;
import io.github.pedrozaz.axiom.analyzer.application.port.out.ContentRepositoryPort;
import io.github.pedrozaz.axiom.analyzer.domain.service.ContentAnalyzerService;
import io.github.pedrozaz.axiom.ingestor.domain.model.AnalysisResult;
import io.github.pedrozaz.axiom.ingestor.domain.model.ContentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class AnalyzeContentUseCaseImpl implements AnalyzeContentUseCase {

    private final ContentRepositoryPort contentRepository;
    private final ContentAnalyzerService contentAnalyzerService;
    private final ContentEventPublisherPort contentEventPublisher;

    public AnalyzeContentUseCaseImpl(ContentRepositoryPort contentRepository,
                                     ContentAnalyzerService contentAnalyzerService,
                                     ContentEventPublisherPort contentEventPublisher) {
        this.contentAnalyzerService = contentAnalyzerService;
        this.contentRepository = contentRepository;
        this.contentEventPublisher = contentEventPublisher;
    }

    @Override
    @Transactional
    public void analyzeContent(UUID contentId) {
        contentRepository.findById(contentId).ifPresent(content -> {

            AnalysisResult result = contentAnalyzerService.analyze(content);
            var analyzedContent = content.toBuilder()
                    .status(ContentStatus.ANALYSIS_COMPLETE)
                    .analysisResult(result)
                    .updatedAt(LocalDateTime.now())
                    .build();

            contentRepository.save(analyzedContent);
            contentEventPublisher.publishContentAnalyzedEvent(analyzedContent);
        });
    }
}
