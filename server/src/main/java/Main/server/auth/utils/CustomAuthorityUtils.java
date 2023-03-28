package Main.server.auth.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthorityUtils {
    @Value("${mail.address.admin}")
    private String adminMailAddress;

    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
    private final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    //    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_ENFP", "ROLE_ENFJ", "ROLE_ENTP", "ROLE_ENTJ", "ROLE_ESFP", "ROLE_ESFJ", "ROLE_ESTP", "ROLE_ESTJ", "ROLE_INFP",
//            "ROLE_INFJ", "ROLE_INTP", "ROLE_INTJ", "ROLE_ISFP", "ROLE_ISFJ", "ROLE_ISTP", "ROLE_ISTJ", "ROLE_GUEST");
//    private final List<GrantedAuthority> ENFP_ROLES = AuthorityUtils.createAuthorityList("ROLE_ENFP", "ROLE_GUEST");
//    private final List<GrantedAuthority> ENFJ_ROLES = AuthorityUtils.createAuthorityList("ROLE_ENFJ", "ROLE_GUEST");
//    private final List<GrantedAuthority> ENTP_ROLES = AuthorityUtils.createAuthorityList("ROLE_ENTP", "ROLE_GUEST");
//    private final List<GrantedAuthority> ENTJ_ROLES = AuthorityUtils.createAuthorityList("ROLE_ENTJ", "ROLE_GUEST");
//    private final List<GrantedAuthority> ESFP_ROLES = AuthorityUtils.createAuthorityList("ROLE_ESFP", "ROLE_GUEST");
//    private final List<GrantedAuthority> ESFJ_ROLES = AuthorityUtils.createAuthorityList("ROLE_ESFJ", "ROLE_GUEST");
//    private final List<GrantedAuthority> ESTP_ROLES = AuthorityUtils.createAuthorityList("ROLE_ESTP", "ROLE_GUEST");
//    private final List<GrantedAuthority> ESTJ_ROLES = AuthorityUtils.createAuthorityList("ROLE_ESTJ", "ROLE_GUEST");
//    private final List<GrantedAuthority> INFP_ROLES = AuthorityUtils.createAuthorityList("ROLE_INFP", "ROLE_GUEST");
//    private final List<GrantedAuthority> INFJ_ROLES = AuthorityUtils.createAuthorityList("ROLE_INFJ", "ROLE_GUEST");
//    private final List<GrantedAuthority> INTP_ROLES = AuthorityUtils.createAuthorityList("ROLE_INTP", "ROLE_GUEST");
//    private final List<GrantedAuthority> INTJ_ROLES = AuthorityUtils.createAuthorityList("ROLE_INTJ", "ROLE_GUEST");
//    private final List<GrantedAuthority> ISFP_ROLES = AuthorityUtils.createAuthorityList("ROLE_ISFP", "ROLE_GUEST");
//    private final List<GrantedAuthority> ISFJ_ROLES = AuthorityUtils.createAuthorityList("ROLE_ISFJ", "ROLE_GUEST");
//    private final List<GrantedAuthority> ISTP_ROLES = AuthorityUtils.createAuthorityList("ROLE_ISTP", "ROLE_GUEST");
//    private final List<GrantedAuthority> ISTJ_ROLES = AuthorityUtils.createAuthorityList("ROLE_ISTJ", "ROLE_GUEST");
    private final List<GrantedAuthority> GUEST_ROLES = AuthorityUtils.createAuthorityList("ROLE_GUEST");

    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN");
//    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "GUEST", "ENFP", "ENFJ", "ENTP", "ENTJ", "ESFP", "ESFJ", "ESTP", "ESTJ", "INFP", "INFJ", "INTP", "INTJ", "ISFP", "ISFJ", "ISTP", "ISTJ");
//    private final List<String> ENFP_ROLES_STRING = List.of("ENFP", "GUEST");
//    private final List<String> ENFJ_ROLES_STRING = List.of("ENFJ", "GUEST");
//    private final List<String> ENTP_ROLES_STRING = List.of("ENTP", "GUEST");
//    private final List<String> ENTJ_ROLES_STRING = List.of("ENTJ", "GUEST");
//    private final List<String> ESFP_ROLES_STRING = List.of("ESFP", "GUEST");
//    private final List<String> ESFJ_ROLES_STRING = List.of("ESFJ", "GUEST");
//    private final List<String> ESTP_ROLES_STRING = List.of("ESTP", "GUEST");
//    private final List<String> ESTJ_ROLES_STRING = List.of("ESTJ", "GUEST");
//    private final List<String> INFP_ROLES_STRING = List.of("INFP", "GUEST");
//    private final List<String> INFJ_ROLES_STRING = List.of("INFJ", "GUEST");
//    private final List<String> INTP_ROLES_STRING = List.of("INTP", "GUEST");
//    private final List<String> INTJ_ROLES_STRING = List.of("INTJ", "GUEST");
//    private final List<String> ISFP_ROLES_STRING = List.of("ISFP", "GUEST");
//    private final List<String> ISFJ_ROLES_STRING = List.of("ISFJ", "GUEST");
//    private final List<String> ISTP_ROLES_STRING = List.of("ISTP", "GUEST");
//    private final List<String> ISTJ_ROLES_STRING = List.of("ISTJ", "GUEST");
    private final List<String> USER_ROLES_STRING = List.of("USER");
    private final List<String> GUEST_ROLES_STRING = List.of("GUEST");

    // 메모리 상의 Role을 기반으로 권한 정보 생성.
    public List<GrantedAuthority> createAuthorities(String email) {
        if (email.equals(adminMailAddress)) {
            return ADMIN_ROLES;
        }else if(email.contains("guest")){
            return GUEST_ROLES;
        } else{
            return USER_ROLES;
        }
//        }else if(mbti.toUpperCase().equals("ENFP")){
//            return ENFP_ROLES;
//        }else if(mbti.toUpperCase().equals("ENFJ")){
//            return ENFJ_ROLES;
//        }else if(mbti.toUpperCase().equals("ENTP")){
//            return ENTP_ROLES;
//        }else if(mbti.toUpperCase().equals("ENTJ")){
//            return ENTJ_ROLES;
//        }else if(mbti.toUpperCase().equals("ESFP")){
//            return ESFP_ROLES;
//        }else if(mbti.toUpperCase().equals("ESFJ")){
//            return ESFJ_ROLES;
//        }else if(mbti.toUpperCase().equals("ESTP")){
//            return ESTP_ROLES;
//        }else if(mbti.toUpperCase().equals("ESTJ")){
//            return ESTJ_ROLES;
//        }else if(mbti.toUpperCase().equals("INFP")){
//            return INFP_ROLES;
//        }else if(mbti.toUpperCase().equals("INFJ")){
//            return INFJ_ROLES;
//        }else if(mbti.toUpperCase().equals("INTP")){
//            return INTP_ROLES;
//        }else if(mbti.toUpperCase().equals("INTJ")){
//            return INTJ_ROLES;
//        }else if(mbti.toUpperCase().equals("ISFP")){
//            return ISFP_ROLES;
//        }else if(mbti.toUpperCase().equals("ISFJ")){
//            return ISFJ_ROLES;
//        }else if(mbti.toUpperCase().equals("ISTP")){
//            return ISTP_ROLES;
//        }else if(mbti.toUpperCase().equals("ISTJ")){
//            return ISTJ_ROLES;
//        }
    }

    // DB에 저장된 Role을 기반으로 권한 정보 생성
    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return authorities;
    }

    // DB 저장 용
    public List<String> createRoles(String email) {
        if (email.equals(adminMailAddress)) {
            return ADMIN_ROLES_STRING;
        }else if(email.contains("guest")){
            return GUEST_ROLES_STRING;
        }else{
            return USER_ROLES_STRING;
        }
//        }else if(mbti.toUpperCase().equals("ENFP")){
//            return ENFP_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ENFJ")){
//            return ENFJ_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ENTP")){
//            return ENTP_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ENTJ")){
//            return ENTJ_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ESFP")){
//            return ESFP_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ESFJ")){
//            return ESFJ_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ESTP")){
//            return ESTP_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ESTJ")){
//            return ESTJ_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("INFP")){
//            return INFP_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("INFJ")){
//            return INFJ_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("INTP")){
//            return INTP_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("INTJ")){
//            return INTJ_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ISFP")){
//            return ISFP_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ISFJ")){
//            return ISFJ_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ISTP")){
//            return ISTP_ROLES_STRING;
//        }else if(mbti.toUpperCase().equals("ISTJ")){
//            return ISTJ_ROLES_STRING;
//        }
    }
}