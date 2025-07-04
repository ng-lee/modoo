package com.modoo.domain.member.service.userinfo;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOauth2UserInfo(String provider, Map<String, Object> attributes) {
        switch (provider) {
            case "google" -> {
                return new GoogleUserInfo(attributes);
            }
        }
        throw new OAuth2AuthenticationException("invalid provider type");
    }
}
