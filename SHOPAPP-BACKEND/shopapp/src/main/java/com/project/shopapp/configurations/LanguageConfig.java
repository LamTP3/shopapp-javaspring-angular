package com.project.shopapp.configurations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class LanguageConfig {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n.messages"); // Tên cơ sở của tệp tài liệu ngôn ngữ
        messageSource.setDefaultEncoding("UTF-8"); // Đặt mã hóa mặc định
        return messageSource;
    }
}
