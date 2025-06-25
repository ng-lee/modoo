package com.modoo.domain.clubs.entity;

import com.modoo.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "clubs_file")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ClubsFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubsFileCd;

    @Column(length = 20, nullable = false)
    @Comment(value = "모임 코드")
    private String clubsCd;

    @Comment(value = "파일 코드")
    private String fileCd;

}
