package io.github.pedrozaz.axiom.application.port.in;

import io.github.pedrozaz.axiom.domain.model.Content;

public interface SubmitContentUseCase {
    Content submit(Content content);
}
