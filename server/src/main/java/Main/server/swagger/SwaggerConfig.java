package Main.server.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private String title;
    private String description;

    @Bean
    public Docket UserApi(){

        title = "User Api 문서";
        description = "User Api 자동화 문서";

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("USER")
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("Main.server.user.controller"))
                .paths(PathSelectors.ant("/users/**"))
                .build()
                .apiInfo(apiInfo(title, description));
    }

    @Bean
    public Docket BoardIntegratedApi(){

        title = "Board Integrated Api 문서";
        description = "Board Integrated Api 자동화 문서";

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("BoardIntegrated")
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("Main.server.board_integrated.controller"))
                .paths(PathSelectors.ant("/board/integrated/**"))
                .build()
                .apiInfo(apiInfo(title, description));
    }


    private ApiInfo apiInfo(String title, String description){
        return new ApiInfo(
                title,
                description,
                "1.0",
                "https://github.com/codestates-seb/seb42_main_033",
                new Contact("name", "url", "email"),
                "license",
                "licenseUrl",
                new ArrayList<>()
        );
    }
}
