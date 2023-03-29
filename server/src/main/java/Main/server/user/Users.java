package Main.server.user;

import Main.server.audit.Auditable;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    // 권한 부여용 추가 (이현수)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    // test
    public Users(long userId, String nickName, String email, String password1, String password2, String mbti) {
        this.userId = userId;
        this.nickName = nickName;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.mbti = mbti;
    }

    public Users(String email){
        this.email = email;
    }
}
