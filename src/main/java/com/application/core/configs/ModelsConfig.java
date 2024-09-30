package com.application.core.configs;

import com.application.models.vectors.Vector;
import com.application.models.vectors.IVector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ModelsConfig {
    @Bean
    public IVector vector() {
        return new Vector();
    }
}
