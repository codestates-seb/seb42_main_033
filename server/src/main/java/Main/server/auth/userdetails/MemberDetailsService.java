package Main.server.auth.userdetails;

import Main.server.advice.BusinessLogicalException;
import Main.server.advice.ExceptionCode;
import Main.server.auth.utils.CustomAuthorityUtils;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Optional;

@Component
public class MemberDetailsService implements UserDetailsService{
    private final UserRepository userRepository;
    private final CustomAuthorityUtils customAuthorityUtils;

    public MemberDetailsService(UserRepository userRepository, CustomAuthorityUtils customAuthorityUtils) {
        this.userRepository = userRepository;
        this.customAuthorityUtils = customAuthorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<Users> optionalUser = userRepository.findByEmail(email);
        Users findUser = optionalUser.orElseThrow(() -> new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));

        return new MemberDetails(findUser);
    }

    private final class MemberDetails extends Users implements UserDetails{
        MemberDetails(Users users){
            setUserId(users.getUserId());
            setEmail(users.getEmail());
            setPassword1(users.getPassword1());
            setPassword2(users.getPassword2());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return customAuthorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    }


}