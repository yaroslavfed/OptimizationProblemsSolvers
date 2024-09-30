package com.application.core.configs;

import com.application.functionals.io.IFunctional;
import com.application.functionals.MyFunctional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class FunctionalsConfig {
    @Bean(name = "myFunctional")
    public IFunctional myFunctional() {
        return new MyFunctional();
    }
}
