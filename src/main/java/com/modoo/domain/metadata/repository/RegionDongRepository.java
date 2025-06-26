package com.modoo.domain.metadata.repository;

import com.modoo.domain.metadata.entity.RegionDong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionDongRepository extends JpaRepository<RegionDong, Long> {
    List<RegionDong> findBySigunguCd(Integer sigunguCd);
}
