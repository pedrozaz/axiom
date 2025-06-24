package io.github.pedrozaz.axiom.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class Content {

    private UUID id;
    private UUID userId;
    private String textContent;
    private ContentStatus status;
    private AnalysisResult analysisResult;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
