package Main.server.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public static final String ALLOWED_METHOD_NAME = "GET,HEAD,POST,PUT,DELETE,OPTIONS,PATCH";

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOriginPatterns("*", "http://seb42-main-033-bucket.s3-website.ap-northeast-2.amazonaws.com:8080",
                        "ec2-52-78-241-208.ap-northeast-2.compute.amazonaws.com:8080",
                        "http://seb42-main-033-bucket.s3-website.ap-northeast-2.amazonaws.com",
                        "ec2-52-78-241-208.ap-northeast-2.compute.amazonaws.com")
                .allowedHeaders("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS","PATCH")
                .allowCredentials(true)
                .maxAge(3000);
    }
}

