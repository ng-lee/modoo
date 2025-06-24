package com.modoo.global.filter;

import com.modoo.domain.member.service.AuthService;
import com.modoo.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    /**
     * jwt 필터 제외 경로 세팅
     *
     * @param request current HTTP request
     * @return
     * @throws ServletException
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        AntPathMatcher antPathMatcher = new AntPathMatcher();

        String[] patterns = {"/css/**", "/assets/**", "/favicon.ico", "/login", "/join"};
        String path = request.getRequestURI();
        String method = request.getMethod();
        boolean isPatternMatched = Arrays.stream(patterns)
                .anyMatch(pattern -> antPathMatcher.match(pattern, path));

//        log.info("/// request path: {}, exclude url: {} ///", path, isPatternMatched);
        return isPatternMatched;
    }

    /**
     * jwt 인증 필터
     * access token 만료 시
     *  ㄴ refresh token 유효 시 access token 재발급
     *  ㄴ refresh token 만료 시 로그인 페이지
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        log.info("/// {} - JwtFilter ///", request.getRequestURI());

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = null;
        String refreshToken = null;

        for (Cookie cookie : cookies) {
            if ("access-token".equals(cookie.getName())) {
                accessToken = cookie.getValue();
            } else if ("refresh-token".equals(cookie.getName())) {
                refreshToken = cookie.getValue();
            }
        }

        try {
            // access token 유효성 검증 및 인증 세팅
            jwtUtil.validateToken(accessToken);
            Claims claims = jwtUtil.getClaims(accessToken);
            String memberCd = String.valueOf(claims.get("memberCd"));

            setAuthentication(memberCd);
        } catch (ExpiredJwtException accessExpiredException) {
            // access token 만료된 경우
            log.error("Access token error", accessExpiredException);

            try {
                // refresh token 유효할 경우 access token 재발급
                jwtUtil.validateToken(refreshToken);
                Claims claims = jwtUtil.getClaims(refreshToken);
                String memberCd = String.valueOf(claims.get("memberCd"));

                //db에 저장된 refresh token과 비교
                boolean isMatched = authService.checkRefreshToken(Long.parseLong(memberCd), refreshToken);
                if (isMatched) {
                    //access token 재발급
                    String newAccessToken = jwtUtil.createAccessToken(memberCd);
                    setAuthentication(memberCd);

                    //cookie 세팅
                    Cookie accessTokenCookie = new Cookie("access-token", newAccessToken);
                    accessTokenCookie.setMaxAge(60 * 60);
                    accessTokenCookie.setHttpOnly(true);
                    response.addCookie(accessTokenCookie);
                }
            } catch (ExpiredJwtException refreshExpiredException) {
                log.error("Refresh token error", refreshExpiredException);
            } catch (Exception e) {
                log.error("Refresh token validation error", e);
            }
        } catch (Exception e) {
            log.error("Access token validation error", e);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 인증 객체 생성 및 SecurityContext에 저장
     *
     * @param memberCd
     */
    private void setAuthentication(String memberCd) {
        UserDetails userDetails = authService.loadUserByUsername(memberCd);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
