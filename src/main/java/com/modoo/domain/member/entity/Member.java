package com.modoo.domain.member.entity;

import com.modoo.domain.member.dto.request.MemberRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cd;

    @Column(length = 20)
    private String id;

    @Column(length = 255)
    private String password;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 10, nullable = false)
    private Integer categoryCd;

    @Column(length = 10, nullable = false)
    private Integer regionCd;

    public static Member of(MemberRequest request) {
        return Member.builder()
                .id(request.getId())
                .password(request.getPassword())
                .name(request.getName())
                .phone(request.getPhone())
                .categoryCd(request.getCategoryCd())
                .regionCd(request.getRegionCd())
                .build();
    }

}
