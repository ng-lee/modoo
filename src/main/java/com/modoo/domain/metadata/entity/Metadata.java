package com.modoo.domain.metadata.entity;

import com.modoo.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "metadata")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Metadata extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metadataCd;

    @Column(length = 20)
    @Comment(value = "메타데이터 타입")
    private String metadataType;

    @Column(length = 50, nullable = false)
    @Comment(value = "내용")
    private String contents;

    @Column(length = 10, nullable = false)
    @Comment(value = "순서")
    private Integer metadataOrder;

}
