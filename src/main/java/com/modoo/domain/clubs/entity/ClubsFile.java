package com.modoo.domain.clubs.entity;

import com.modoo.domain.common.entity.BaseEntity;
import com.modoo.global.entity.ImageFile;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clubs_cd")
    @Comment(value = "모임 코드")
    private Clubs clubs;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_cd")
    @Comment(value = "파일 코드")
    private ImageFile imageFile;

}
