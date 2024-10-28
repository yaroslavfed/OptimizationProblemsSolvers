package com.application.core.configs;

import com.application.functionals.io.Functional;
import com.application.functionals.MyFunctionalImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class FunctionalsConfig {
    @Bean(name = "myFunctional")
    public Functional myFunctional() {
        return new MyFunctionalImpl();
    }
}
