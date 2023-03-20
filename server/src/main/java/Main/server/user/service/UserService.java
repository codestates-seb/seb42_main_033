package Main.server.user.service;

import Main.server.advice.BusinessLogicalException;
import Main.server.advice.ExceptionCode;
import Main.server.auth.utils.CustomAuthorityUtils;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;


    // 로그인, 시큐리티용 추가 (이현수)
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils authorityUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    public Users createUser(Users users) throws Exception {
        verifyExistedEmail(users.getEmail());

        // Password 암호화 (이현수)
        String encryptedPassword = passwordEncoder.encode(users.getPassword1());
        users.setPassword1(encryptedPassword);

        // Roles 저장 (이현수)
        List<String> roles = authorityUtils.createRoles(users.getEmail());
        users.setRoles(roles);

        Users createdUsers = userRepository.save(users);
        return createdUsers;
    }

    public Users updateUser(Users users) throws Exception {
        Users findUsers = findVerifiedUser(users.getUserId());
        verifyExistedUserNickName(users.getNickName());

        Optional.ofNullable(users.getNickName())
                .ifPresent(findUsers::setNickName);
        Optional.ofNullable(users.getPassword1())
                .ifPresent(findUsers::setPassword1);
        Optional.ofNullable(users.getPassword2())
                .ifPresent(findUsers::setPassword2);
        Optional.ofNullable(users.getMbti())
                .ifPresent(findUsers::setMbti);

        return userRepository.save(findUsers);
    }

    public Users getUser(long userId) {
        return findVerifiedUser(userId);
    }

    public Page<Users> getUsers(int page) {
        if(userRepository.findAll() == null) {
            throw new BusinessLogicalException(ExceptionCode.NULL_POINT_ERROR);
        }
        return userRepository.findAll(PageRequest.of(page-1, 10, Sort.by("userId").descending()));
    }

    public void deleteUser(long userId) {
        Users deleteUsers = findVerifiedUser(userId);
        userRepository.delete(deleteUsers);
    }

    public void verifyExistedEmail(String email) throws Exception {
        Optional<Users> foundEmail = userRepository.findByEmail(email);
        if(foundEmail.isPresent())
            throw new BusinessLogicalException(ExceptionCode.EMAIL_ALREADY_EXIST);
    }

    public Users findVerifiedUser(long userId) {
        Optional<Users> optionalUsers =
                userRepository.findById(userId);
        Users findUsers =
                optionalUsers.orElseThrow(() ->
                        new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));
        return findUsers;
    }

    public Users loadExistedUser(long userId) {
        Users findUsers = userRepository.findById(userId).orElse(null);
        if(findUsers == null) {
            throw new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return findUsers;
    }

    // 존재하는 닉네임인지 확인
    public void verifyExistedUserNickName(String nickName) {
        Users foundUserName = userRepository.findByNickName(nickName);
        if(foundUserName != null)
            throw new BusinessLogicalException(ExceptionCode.NICKNAME_ALREADY_EXIST);
    }

}
