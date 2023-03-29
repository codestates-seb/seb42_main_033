package Main.server.auth.handler;

import Main.server.auth.userdetails.MemberDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        log.info("정상적으로 인증완료");
        System.out.println(authentication.getAuthorities());
        if(authentication.getAuthorities().contains("ROLE_USER")){
            System.out.println("aa");
        }
    }
}