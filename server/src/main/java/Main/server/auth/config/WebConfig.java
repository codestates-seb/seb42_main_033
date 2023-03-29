package Main.server.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOriginPatterns("*", "http://seb42-main-033-bucket.s3-website.ap-northeast-2.amazonaws.com:8080",
                        "ec2-3-39-235-30.ap-northeast-2.compute.amazonaws.com:8080",
                        "http://seb42-main-033-bucket.s3-website.ap-northeast-2.amazonaws.com",
                        "ec2-3-39-235-30.ap-northeast-2.compute.amazonaws.com")
                .allowedHeaders("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS","PATCH")
                .exposedHeaders("*")
                .allowCredentials(true)
                .maxAge(3000);
    }
}