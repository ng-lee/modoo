package com.modoo.domain.metadata.repository;

import com.modoo.domain.metadata.entity.RegionSido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionSidoRepository extends JpaRepository<RegionSido, Long> {

    @Query(value = """
            select rs
            from RegionSido rs
            join fetch rs.sigunguList 
            """)
    List<RegionSido> findAllSidoSigungu();
}
