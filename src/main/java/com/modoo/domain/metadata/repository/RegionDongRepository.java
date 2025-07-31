package com.modoo.domain.metadata.repository;

import com.modoo.domain.metadata.entity.RegionDong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionDongRepository extends JpaRepository<RegionDong, Long> {
    List<RegionDong> findBySigunguCd_sigunguCd(Long sigunguCd);

    @Query(value = """
            select rd
            from RegionDong rd
            join fetch rd.sigunguCd
            where rd.dongCd = :dongCd
            """
    )
    RegionDong getInitialRegionData(Long dongCd);
}
