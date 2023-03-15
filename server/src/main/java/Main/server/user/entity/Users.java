package Main.server.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

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

}
