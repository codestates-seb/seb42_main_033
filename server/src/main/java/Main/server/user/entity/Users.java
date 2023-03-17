package Main.server.user.entity;

import Main.server.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(unique = true, nullable = false)
    private String nickName;

    @Email
    @Column(unique = true, updatable = false, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password1;

    @Column(length = 100, nullable = false)
    private String password2;

    @Column(nullable = false)
    private String mbti;

    // test
    public Users(long userId, String nickName, String email, String password1, String password2, String mbti) {
        this.userId = userId;
        this.nickName = nickName;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.mbti = mbti;
    }
}
