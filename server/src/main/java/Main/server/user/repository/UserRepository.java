package Main.server.user.repository;

import Main.server.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByEmail(String email);
    public Users findById(long userId);
    public Optional<Users> findByNickName(String nickName);
}