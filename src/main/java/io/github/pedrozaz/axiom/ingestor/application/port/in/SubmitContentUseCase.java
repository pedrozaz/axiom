package io.github.pedrozaz.axiom.ingestor.application.port.in;

import io.github.pedrozaz.axiom.ingestor.domain.model.Content;

public interface SubmitContentUseCase {
    Content submit(Content content);
}
