package com.modoo.domain.member.feign;

import com.modoo.global.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "googleClient", url = "https://oauth2.googleapis.com", configuration = FeignConfig.class)
public interface GoogleClient {

}
