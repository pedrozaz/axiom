package io.github.pedrozaz.axiom.ingestor.application.port;

import io.github.pedrozaz.axiom.ingestor.application.port.in.SubmitContentUseCase;
import io.github.pedrozaz.axiom.ingestor.application.port.out.ContentEventPublisherPort;
import io.github.pedrozaz.axiom.ingestor.application.port.out.ContentRepositoryPort;
import io.github.pedrozaz.axiom.ingestor.domain.model.Content;
import io.github.pedrozaz.axiom.ingestor.domain.model.ContentStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SubmitContentUseCaseImpl implements SubmitContentUseCase {

    private final ContentRepositoryPort contentRepository;
    private final ContentEventPublisherPort contentEventPublisher;

    public SubmitContentUseCaseImpl(ContentRepositoryPort contentRepository,
                                    ContentEventPublisherPort contentEventPublisher) {
        this.contentRepository = contentRepository;
        this.contentEventPublisher = contentEventPublisher;
    }

    @Override
    @Transactional
    public Content submit(Content content) {

        var contentToSave = content.toBuilder()
                // UUID setted for testing purposes
                .userId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .status(ContentStatus.PENDING_ANALYSIS)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Content savedContent = contentRepository.save(contentToSave);
        contentEventPublisher.publishContentSubmittedEvent(savedContent);

        return savedContent;

    }
}
