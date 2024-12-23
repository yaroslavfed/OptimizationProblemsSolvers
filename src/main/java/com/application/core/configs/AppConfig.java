package com.application.core.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.application.models", "com.application.services"})
public class AppConfig {
}
