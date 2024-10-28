package com.application.core.configs;

import com.application.functions.io.ParametricFunction;
import com.application.functions.LineFunctionImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class FunctionsConfig {
    @Bean(name = "lineFunction")
    public ParametricFunction lineFunction() {
        return new LineFunctionImpl();
    }
}
