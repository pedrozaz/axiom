package io.github.pedrozaz.axiom.ingestor.infraestructure.persistence.mapper;

import io.github.pedrozaz.axiom.ingestor.domain.model.Content;
import io.github.pedrozaz.axiom.ingestor.infraestructure.persistence.entity.ContentJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    ContentJpaEntity toEntity(Content content);

    @Mapping(target = "analysisResult", ignore = true)
    Content toDomain(ContentJpaEntity entity);
}
