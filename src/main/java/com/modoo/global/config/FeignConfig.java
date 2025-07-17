package com.modoo.global.config;

import feign.Logger;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.modoo")
public class FeignConfig {

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

    // json -> java object
    @Bean
    public Decoder decoder() {
        return new JacksonDecoder();
    }

}
