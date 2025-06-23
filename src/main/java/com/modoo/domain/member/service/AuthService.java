package com.modoo.domain.member.service;

import com.modoo.domain.member.dto.auth.MemberAuth;
import com.modoo.domain.member.dto.request.LoginRequest;
import com.modoo.domain.member.entity.Member;
import com.modoo.domain.member.repository.MemberRepository;
import com.modoo.global.dto.jwt.AuthenticationDto;
import com.modoo.global.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Transactional
    public AuthenticationDto login(LoginRequest request) {
        Member member = memberRepository.findByMemberId(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("회원 정보가 존재하지 않습니다."));

        //비밀번호 체크
        String password = request.getPassword();
        if (!bCryptPasswordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        //jwt 생성
        AuthenticationDto authenticationDto = jwtUtil.createToken(String.valueOf(member.getMemberCd()));
        member.updateRefreshToken(authenticationDto.getRefreshToken());
        member.updateRefreshTokenExpirationTime(authenticationDto.getRefreshTokenExpirationTime());

        memberRepository.save(member);
        return authenticationDto;
    }

    public boolean checkRefreshToken(Long memberCd, String refreshToken) {
        Member member = memberRepository.findById(memberCd)
                .orElseThrow(() -> new RuntimeException("회원 정보가 존재하지 않습니다."));

        if(refreshToken.equals(member.getRefreshToken())) {
            return true;
        } else{
            throw new RuntimeException("refresh token 유효성 체크 에러");
        }
    }

    //필터에서 체크 후 securitycontext에 넣는용
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(Long.valueOf(username))
                .orElseThrow(() -> new RuntimeException("회원 정보가 존재하지 않습니다."));

        return MemberAuth.of(member);
    }
}
