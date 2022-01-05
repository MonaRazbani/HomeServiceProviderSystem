package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import validation.ControlInput;

@Configuration

public class ValidationConfig {
    @Bean
    public ControlInput controlInput (){
        return new ControlInput();
    }
}
