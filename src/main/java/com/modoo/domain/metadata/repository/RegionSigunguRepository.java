package com.modoo.domain.metadata.repository;

import com.modoo.domain.metadata.entity.RegionSigungu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionSigunguRepository extends JpaRepository<RegionSigungu, Long> {
    List<RegionSigungu> findBySidoCd_sidoCd(Long sidoCd);
}
