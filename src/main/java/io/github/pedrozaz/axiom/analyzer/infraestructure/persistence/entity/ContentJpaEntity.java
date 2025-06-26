package io.github.pedrozaz.axiom.analyzer.infraestructure.persistence.entity;

import io.github.pedrozaz.axiom.ingestor.domain.model.AnalysisResult;
import io.github.pedrozaz.axiom.ingestor.domain.model.ContentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "contents")
@Data
public class ContentJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String textContent;

    @Column(nullable = false)
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentStatus status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private AnalysisResult analysisResult;

    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
