package com.modoo.domain.metadata.service;

import com.modoo.domain.metadata.dto.MetaDataDto;
import com.modoo.domain.metadata.repository.MetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetaDataService {

    private final MetadataRepository metadataRepository;

    @Cacheable(value = "metadata", key = "#type")
    public List<MetaDataDto> findByMetadataType(String type) {
        return metadataRepository.findByMetadataTypeOrderByMetadataOrder(type).stream()
                .map(MetaDataDto::fromEntity)
                .collect(Collectors.toList());
    }

}
