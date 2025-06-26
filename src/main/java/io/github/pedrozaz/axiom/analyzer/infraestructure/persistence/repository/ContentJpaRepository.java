package io.github.pedrozaz.axiom.analyzer.infraestructure.persistence.repository;

import io.github.pedrozaz.axiom.analyzer.infraestructure.persistence.entity.ContentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContentJpaRepository extends JpaRepository<ContentJpaEntity, UUID> {
}
