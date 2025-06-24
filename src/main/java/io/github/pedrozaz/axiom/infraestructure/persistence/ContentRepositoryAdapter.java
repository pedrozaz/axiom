package io.github.pedrozaz.axiom.infraestructure.persistence;

import io.github.pedrozaz.axiom.application.port.out.ContentRepositoryPort;
import io.github.pedrozaz.axiom.domain.model.Content;
import io.github.pedrozaz.axiom.infraestructure.persistence.entity.ContentJpaEntity;
import io.github.pedrozaz.axiom.infraestructure.persistence.mapper.ContentMapper;
import io.github.pedrozaz.axiom.infraestructure.persistence.repository.ContentJpaRepository;
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
