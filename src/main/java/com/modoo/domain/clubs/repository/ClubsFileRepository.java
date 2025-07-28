package com.modoo.domain.clubs.repository;

import com.modoo.domain.clubs.entity.Clubs;
import com.modoo.domain.clubs.entity.ClubsFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubsFileRepository extends JpaRepository<ClubsFile, Long> {

}
