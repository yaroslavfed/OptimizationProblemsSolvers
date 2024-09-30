package com.application.core.configs;

import com.application.functions.io.IParametricFunction;
import com.application.functions.LineFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class FunctionsConfig {
    @Bean(name = "lineFunction")
    public IParametricFunction lineFunction() {
        return new LineFunction();
    }
}
