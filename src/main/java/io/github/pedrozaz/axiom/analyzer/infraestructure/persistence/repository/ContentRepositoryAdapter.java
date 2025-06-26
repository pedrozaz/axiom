package io.github.pedrozaz.axiom.analyzer.infraestructure.persistence.repository;

import io.github.pedrozaz.axiom.analyzer.application.port.out.ContentRepositoryPort;
import io.github.pedrozaz.axiom.ingestor.domain.model.Content;
import io.github.pedrozaz.axiom.analyzer.infraestructure.persistence.entity.ContentJpaEntity;
import io.github.pedrozaz.axiom.analyzer.infraestructure.persistence.mapper.ContentMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ContentRepositoryAdapter implements ContentRepositoryPort {

    private final ContentJpaRepository jpaRepository;
    private final ContentMapper mapper;

    public ContentRepositoryAdapter(ContentJpaRepository jpaRepository,
                                    ContentMapper contentMapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = contentMapper;
    }

    @Override
    public Content save(Content content) {
        ContentJpaEntity entity = mapper.toEntity(content);
        ContentJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Content> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
