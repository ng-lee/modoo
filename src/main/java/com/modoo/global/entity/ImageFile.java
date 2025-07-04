package com.modoo.global.entity;

import com.modoo.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "image_file")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ImageFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileCd;

    @Column(nullable = false)
    @Comment(value = "업로드 경로")
    private String uploadPath;

    @Column(nullable = false)
    @Comment(value = "원본 파일명")
    private String originalName;

    @Column(nullable = false)
    @Comment(value = "서버에 저장된 파일명 (UUID)")
    private String saveName;

    @Column(length = 20)
    @Comment("파일 확장자 (예: jpg, png, pdf)")
    private String extension;

    @Column(nullable = false)
    @Comment(value = "순서")
    private Integer fileOrder;

}
