package com.modoo.domain.metadata.repository;

import com.modoo.domain.metadata.entity.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetadataRepository extends JpaRepository<Metadata, Long> {
    List<Metadata> findByMetadataTypeOrderByMetadataOrder(String metadataType);
}
