package Main.server.auth.config;

import Main.server.auth.JwtTokenizer;
import Main.server.auth.filter.JwtAuthenticationFilter;
import Main.server.auth.filter.JwtverificationFilter;
import Main.server.auth.handler.*;
import Main.server.auth.utils.CustomAuthorityUtils;
import Main.server.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsUtils;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final UserService userService;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils, @Lazy UserService userService) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new UserAuthenticationEntryPoint())
                .accessDeniedHandler(new UserAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/*/users/join").permitAll()
                        .antMatchers(HttpMethod.PATCH, "/*/users/**").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/*/board/integrated").permitAll()
                        .antMatchers(HttpMethod.GET, "/*/board/integrated/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/*/board/**").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.PATCH, "/*/board/**").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.DELETE, "/*/board/**").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.POST, "/*/board/*/*/comment").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.DELETE, "/*/board/*/*/comment/**").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.POST ,"/users/login").permitAll()
                        .antMatchers(HttpMethod.DELETE ,"/users/**").hasAnyRole("ADMIN", "USER")
                )
                .oauth2Login(oauth2 -> oauth2.successHandler(new OAuth2UserSuccessHandler(jwtTokenizer, authorityUtils, userService)));

        http.logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .deleteCookies("JSESSIONID", "remember-me");

        return http.build();
    }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource(){

            CorsConfiguration configuration = new CorsConfiguration();

            configuration.addAllowedOriginPattern("*");
            configuration.addAllowedHeader("*");
            configuration.addAllowedMethod("*");
            configuration.setAllowCredentials(true);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);

            return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity>{
        @Override
        public void configure(HttpSecurity builder) throws Exception{
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/user/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new UserAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());

            JwtverificationFilter jwtverificationFilter = new JwtverificationFilter(jwtTokenizer, authorityUtils);

            builder.addFilter(jwtAuthenticationFilter)
//                    .addFilterAfter(jwtverificationFilter, JwtverificationFilter.class)
                    .addFilterAfter(jwtverificationFilter, OAuth2LoginAuthenticationFilter.class);
        }
    }
}