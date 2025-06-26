package io.github.pedrozaz.axiom.analyzer.infraestructure.persistence.mapper;

import io.github.pedrozaz.axiom.ingestor.domain.model.Content;
import io.github.pedrozaz.axiom.analyzer.infraestructure.persistence.entity.ContentJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    io.github.pedrozaz.axiom.analyzer.infraestructure.persistence.entity.ContentJpaEntity toEntity(Content content);

    @Mapping(target = "analysisResult")
    Content toDomain(ContentJpaEntity entity);
}
