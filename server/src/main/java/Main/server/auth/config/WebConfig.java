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
                .allowedOriginPatterns("*",
                        "https://5293-211-217-72-99.jp.ngrok.io",
                        "https://5293-211-217-72-99.jp.ngrok.io:3000",
                        "http://localhost:3000")
                .allowedMethods(ALLOWED_METHOD_NAME.split(","))
                .allowCredentials(true)
                .maxAge(3000);
    }
}

