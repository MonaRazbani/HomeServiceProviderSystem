package ir.maktab.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan(basePackages = "ir.maktab")
@Import(value = {DatabaseConfig.class })
@Configuration
public class SpringConfig {
  @Bean
    public ModelMapper modelMapper(){
      return new ModelMapper();
  }
}
