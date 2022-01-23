package ir.maktab.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("ir.maktab")
@Import(value = {DatabaseConfig.class })
@Configuration
@EnableWebMvc
public class SpringConfig {
  @Bean
    public ModelMapper modelMapper(){
      return new ModelMapper();
  }

  @Bean(name = "multipartResolver")
  public CommonsMultipartResolver getResolver() {
    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
    //set max upload size per file is 20mb
    commonsMultipartResolver.setMaxUploadSizePerFile(20 * 1024 * 1024);
    return commonsMultipartResolver;
  }
}
