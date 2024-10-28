package com.application.core.configs;

import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ModelsConfig {
    @Bean
    public Vector vector() {
        return new VectorImpl();
    }
}
