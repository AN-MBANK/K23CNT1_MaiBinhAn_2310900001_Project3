package K23CNT1_.Mba_day02.pkg_annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public String appName() {
        return "<h1>VIỆN CÔNG NGHỆ DEVSMASTER</h1><h2>Spring Boot Application</h2>";
    }
}