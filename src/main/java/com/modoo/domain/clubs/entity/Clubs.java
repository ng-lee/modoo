package com.modoo.domain.clubs.entity;

import com.modoo.domain.common.entity.BaseEntity;
import com.modoo.domain.member.dto.request.MemberRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "clubs")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Clubs extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubsCd;

    @Column(length = 20, nullable = false)
    @Comment(value = "모임 이름")
    private String name;

    @Comment(value = "모임 상세 내용")
    private String contents;

    @Column(length = 10, nullable = false)
    @Comment(value = "카테고리 코드")
    private Integer categoryCd;

    @Column(length = 10, nullable = false)
    @Comment(value = "대표이미지 코드")
    private Integer mainFileCd;

    @Column(length = 10, nullable = false)
    @Comment(value = "지역 코드")
    private Integer regionCd;

}
